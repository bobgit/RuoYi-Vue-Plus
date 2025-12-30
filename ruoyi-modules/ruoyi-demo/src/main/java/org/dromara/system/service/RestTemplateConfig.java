package org.dromara.system.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.*;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * RestTemplate 生产级配置
 */
@Slf4j
@Configuration
@EnableRetry   // 开启 @Retryable
public class RestTemplateConfig {

    //现在 Spring 容器持有两个 Closeable Bean，shutdown 时会按依赖逆序先关 2 httpClient，再关1 connectionManager，零泄漏。
    /** 1. 连接池——交给 Spring 关闭 */
    @Bean(destroyMethod = "close")
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(100);          // 总连接数上限
        connectionManager.setDefaultMaxPerRoute(20); // 每个目标主机最大并发连接数（关键！默认是2）
//        connectionManager.setValidateAfterInactivity(TimeUnit.SECONDS.toMillis(10)); // 防止 NAT 超时
        return connectionManager;
    }

    /** 2. HttpClient——交给 Spring 关闭 */
    @Bean(destroyMethod = "close")
    public CloseableHttpClient httpClient(PoolingHttpClientConnectionManager connectionManager) {
        RequestConfig requestConfig = RequestConfig.custom()
            .setConnectionRequestTimeout(Timeout.ofSeconds(3)) // 从连接池获取连接的超时（毫秒）
            .setConnectTimeout(Timeout.ofSeconds(5))           // 建立 TCP 连接的超时
            .setResponseTimeout(Timeout.ofSeconds(8))          // 读取数据的超时（SO_TIMEOUT） java.net.SocketTimeoutException: Read timed out
            .build();

        return HttpClients.custom()
            .setConnectionManager(connectionManager)
            .setDefaultRequestConfig(requestConfig)// 👈 关键：只设这个
            .evictExpiredConnections()
            .evictIdleConnections(TimeValue.ofSeconds(30))
            .build();
    }
    /* ============================ 1. 连接池 ============================ */
    /** 3. 工厂——只依赖 HttpClient Bean，无需再 close */
    @Bean
    public ClientHttpRequestFactory httpRequestFactory(CloseableHttpClient httpClient) {
//        // 连接池管理器 连接池——局部变量
//        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
//        connectionManager.setMaxTotal(200);          // 总连接数
//        connectionManager.setDefaultMaxPerRoute(50); // 单路由最大连接
//
//        // 全局超时（含从池里拿连接的等待时间）HttpClient 5.3 开始 统一通过 RequestConfig 和连接管理器的底层 I/O 配置 来控制。
//        RequestConfig requestConfig = RequestConfig.custom()
//                .setConnectionRequestTimeout(Timeout.ofSeconds(3)) // 从池里拿连接超时
//                .setConnectTimeout(Timeout.ofSeconds(5))         // 建立 TCP 超时
//                .setResponseTimeout(Timeout.ofSeconds(8))        // 读取超时
//                .build();
//          //HttpClient——局部变量
//        CloseableHttpClient httpClient = HttpClients.custom()
//                .setConnectionManager(connectionManager)
//                .setDefaultRequestConfig(requestConfig)// 👈 关键：只设这个
//                .evictExpiredConnections()   // 后台清理过期连接
//                .build();
        //Spring 只把返回的 HttpComponentsClientHttpRequestFactory 注册成 Bean
        // 前两个局部变量既没交给容器，也没手动注册销毁钩子  “资源只要创建，就必须让 Spring 知道怎么销毁。”
        // Spring 只对自己管理的 Bean 调用 DisposableBean.destroy()
        // 局部变量在方法结束就失去强引用，但 HttpClient5 内部还有守护线程（IdleConnectionEvictor、leases 等），不会随 GC 回收；
        //结果：shutdown hook 阶段没人 close() → 资源泄漏。
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    /* ============================ 2. RestTemplate 本体 ============================ */
    /** 4. RestTemplate——常规 */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder,
                                     ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = builder
                .requestFactory(() -> factory)
                .interceptors(List.of(loggingInterceptor(), retryInterceptor())) // 日志 & 重试
                .errorHandler(new DefaultResponseErrorHandler() {               // 统一错误处理
                    @Override
                    public void handleError(ClientHttpResponse response) throws IOException {
                        HttpStatus status = HttpStatus.resolve(response.getStatusCode().value());
                        log.warn("HTTP 调用失败, status={}, {}", status, response.getStatusText());
                        // 这里可抛自定义异常，方便全局异常处理器统一转义
                        super.handleError(response);
                    }
                    // 替换默认错误处理器：让非 2xx 也抛异常（否则 getForObject 不会报错！）
                    @Override
                    protected boolean hasError(HttpStatusCode statusCode) {
                        // 所有非 2xx 都视为错误
                        return !statusCode.is2xxSuccessful();
                    }
                })
                .build();
        return restTemplate;
    }

    /* ============================ 3. 日志拦截器 ============================ */
    private ClientHttpRequestInterceptor loggingInterceptor() {
        return (request, body, execution) -> {
            long start = System.currentTimeMillis();
            ClientHttpResponse response = execution.execute(request, body);
            long cost = System.currentTimeMillis() - start;
            log.info("HTTP 请求 url={}, method={}, status={}, cost={}ms",
                    request.getURI(), request.getMethod(), response.getStatusCode(), cost);
            return response;
        };
    }

    /* ============================ 4. 重试拦截器 ============================ */
    private ClientHttpRequestInterceptor retryInterceptor() {
        // 可重试的异常（网络层 + 5xx）
        Set<Class<? extends Throwable>> retryable = new HashSet<>();
        retryable.add(java.net.SocketTimeoutException.class);
        retryable.add(java.net.ConnectException.class);
        retryable.add(ResourceAccessException.class);
        retryable.add(HttpServerErrorException.class);



        Map<Class<? extends Throwable>, Boolean> retryableExceptions = Map.of(
            ResourceAccessException.class, true,
            ConnectException.class, true,
            SocketTimeoutException.class, true,
            HttpServerErrorException.class, true);
        RetryPolicy retryPolicy = new SimpleRetryPolicy(3, retryableExceptions);
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(retryPolicy); // 最多 3 次
        ExponentialBackOffPolicy backOff = new ExponentialBackOffPolicy();
        backOff.setInitialInterval(300);
        backOff.setMultiplier(2);
        retryTemplate.setBackOffPolicy(backOff);

        return (request, body, execution) -> {
            if(request.getMethod()== HttpMethod.GET){
                try {
                    return retryTemplate.execute(context -> execution.execute(request, body));
                } catch (Throwable th) {
                    //有数据库处理的重试一定要非常小心，因为尽管出现异常，比如远程调用api接口超时异常，这里将会重试，此处的终止并不代表远程停止了，远程会继续执行，目前我的样例故意是休眠5到15秒不等，等远程完成了，这边又重新申请，将会造成每次重试都会往数据库里面插入数据，会有很多冗余数据
                    log.error("HTTP 调用重试 3 次后依然失败, url={}", request.getURI(), th);
                    throw new RestClientException("重试耗尽", th);
                }
            }
            // 非 GET 直接走原逻辑，不重试
            return execution.execute(request, body);
        };
    }
}
