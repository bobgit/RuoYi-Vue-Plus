package org.dromara.system.service;


import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.core5.util.Timeout;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class RestTemplateConfig1 {
//    你给出的 new RestTemplate() 是最原始实例：
//    没有连接池 → 每次 TCP 三次握手
//    没有超时 → 对方 5 秒不回就永远挂起
//    没有 SSL 调优、Keep-Alive、拦截器、日志

    @Bean("batchPool")
    public Executor batchPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        // 核心线程数：根据 CPU + I/O 密集型调整
        executor.setCorePoolSize(20);          // 建议：CPU * 2 ~ 5（I/O 密集型可更大）
        executor.setMaxPoolSize(100);          // 最大线程数（应对突发流量）
        executor.setQueueCapacity(200);        // 有界队列！防止 OOM

        // 线程名前缀（便于排查）
        executor.setThreadNamePrefix("batch-pool-");

        // 拒绝策略：记录日志 + 降级处理
        executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                // 方案1：记录日志并告警
                System.err.println("Task rejected: " + r.toString());
                // 方案2：降级为同步执行（慎用！可能阻塞主线程）
                // r.run();
                // 方案3：写入死信队列 or 抛出自定义异常
                throw new RuntimeException("Task queue is full, task rejected: " + r);
            }
        });

        // 允许核心线程超时回收（可选）
        executor.setAllowCoreThreadTimeOut(true);
        executor.setKeepAliveSeconds(60);

        // 初始化
        executor.initialize();
        return executor;
    }
//    @Bean("batchPool")
//    public ThreadPoolTaskExecutor batchPool() {
//        ThreadPoolTaskExecutor exec = new ThreadPoolTaskExecutor();
//        exec.setCorePoolSize(50);
//        exec.setMaxPoolSize(50);
//        exec.setQueueCapacity(10_000);        // ← 一定要设
//        exec.setThreadNamePrefix("batch-");
//        exec.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); // 降级
//        exec.setWaitForTasksToCompleteOnShutdown(true);
//        exec.setAwaitTerminationSeconds(60);
//        exec.initialize();
//        return exec;
//    }
 /*   @Bean("restTemplate")
    public RestTemplate restTemplate() {
        // 1. 连接池管理器
        PoolingHttpClientConnectionManagerBuilder cmBuilder =
            PoolingHttpClientConnectionManagerBuilder.create()
                .setMaxConnTotal(200)
                .setMaxConnPerRoute(50);

        // 2. 超时
        RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(Timeout.ofSeconds(5))
            .setResponseTimeout(Timeout.ofSeconds(16))
            .build();

        // 3. 创建 HttpClient 5
        CloseableHttpClient httpClient = HttpClients.custom()
            .setConnectionManager(cmBuilder.build())
            .setDefaultRequestConfig(requestConfig)
            .build();

        // 4. Spring Boot 3 专用工厂（Client5）
        HttpComponentsClientHttpRequestFactory factory =
            new HttpComponentsClientHttpRequestFactory(httpClient);

        return new RestTemplate(factory);
    }*/
}
