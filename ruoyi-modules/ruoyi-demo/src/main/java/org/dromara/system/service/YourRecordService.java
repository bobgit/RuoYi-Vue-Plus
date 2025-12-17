// src/main/java/com/example/demo/YourRecordService.java
package org.dromara.system.service;


import cn.hutool.core.util.StrUtil;
import io.github.linpeilie.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.util.Timeout;
import org.dromara.system.domain.SysTaskRecord;
import org.dromara.system.domain.bo.SysTaskRecordBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Service
@Slf4j
@RequiredArgsConstructor
public class YourRecordService {

    private final ISysTaskRecordService sysTaskRecordService;
    private final ExternalServiceClient externalClient;

    // 1. 让 Spring 帮你建好、关好
//    private final RestTemplate restTemplate;   // 你之前写的 @Bean("restTemplate")
//    private final Executor batchPool;          // 你之前写的 @Bean("batchPool")

    /// /    private final Converter converter;
//    @Qualifier("batchPool")
//    private final Executor batchPool;
//        private final ThreadPoolTaskExecutor applicationTaskExecutor;
//    @Qualifier("restTemplate")


    private static final Long MAX_RETRY = 5L;

    /**
     * 创建一个待处理的任务
     */
//    @Transactional
    public void createTask(String data) {
        SysTaskRecordBo record = new SysTaskRecordBo();
        record.setData(data);
        record.setStatus("PENDING");
        sysTaskRecordService.insertByBo(record);
        log.info("【任务创建】成功创建任务 ID: {}, 数据: {}", record.getId(), data);
    }

    /**
     * 定时任务：扫描并重试待处理的任务
     */
//    @Scheduled(fixedDelay = 10_000) // 每10秒执行一次，方便观察
    @Transactional
    public void retryPendingTasks() {
        log.info("【定时任务】开始扫描待处理任务...");
        List<SysTaskRecord> pendingTasks = sysTaskRecordService.findByStatusAndRetryCountLessThan("PENDING", MAX_RETRY);
//        List<SysTaskRecordVo>  pendingTaskList = sysTaskRecordService.queryList(bo);
//        List<SysTaskRecordVo>  pendingTasks = pendingTaskList.stream().filter(t->t.getRetryCount().longValue()<MAX_RETRY).collect(Collectors.toUnmodifiableList());
//        List<YourRecord> pendingTasks = repository.findByStatusAndRetryCountLessThan("PENDING", MAX_RETRY);

        if (pendingTasks.isEmpty()) {
            log.info("【定时任务】没有待处理的任务。");
            return;
        }

        for (SysTaskRecord record : pendingTasks) {
            try {
                log.info("【定时任务】正在处理任务 ID: {}, 当前重试次数: {}", record.getId(), record.getRetryCount());
                externalClient.call(record.getData());
                // 成功
                record.setStatus("SUCCESS");
                log.info("【定时任务】任务 ID: {} 处理成功！", record.getId());
            } catch (Exception e) {
                // 失败
                record.setRetryCount(record.getRetryCount() + 1);
                record.setErrorMsg(e.getMessage());
                log.warn("【定时任务】任务 ID: {} 处理失败，第 {} 次重试。错误信息: {}", record.getId(), record.getRetryCount(), e.getMessage());

                if (record.getRetryCount() >= MAX_RETRY) {
                    record.setStatus("FAILED");
                    log.error("【定时任务】任务 ID: {} 达到最大重试次数，标记为最终失败。", record.getId());
                }
            }
//            SysTaskRecordBo bo1 = converter.convert(record,SysTaskRecordBo.class);
//            sysTaskRecordService.insertByBo(record);
            sysTaskRecordService.insertOrUpdate(record);
        }
    }


    //    private final ThreadPoolTaskExecutor batchPool;
//    @Qualifier("batchPool") // 👈 明确告诉 Spring 用你自己定义的，Spring 就会按名字回退，如果字段名和 Bean 名不一致（你叫 batchPool，但 Bean 也是 batchPool，这倒没问题），但更推荐显式指定，避免未来别人改个名字就注入错池子。
//    private final ThreadPoolTaskExecutor batchPool;
    private final RestTemplate restTemplate;
    @Autowired
    @Qualifier("batchPool")
    private Executor batchPool;

    //    @Async("applicationTaskExecutor")
    public void doBatchBean(String taskId) {
        StopWatch sw = StopWatch.createStarted();

        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 3000; i++) {
            dataList.add(StrUtil.padPre(i + "", 4, "0"));
        }
//        List<String> dataList = IntStream.rangeClosed(1, 1000)
//            .mapToObj(i -> "task id:" + i)
//            .toList();
        String pdfUrl = "http://localhost:8080/system/taskRecord/getPdfTest";
        List<CompletableFuture<List<String>>> futures = dataList.stream()
            .map(item ->
//                CompletableFuture.supplyAsync(
//                    () -> getWithRetry(restTemplate,
//                        UriComponentsBuilder.fromHttpUrl(pdfUrl)
//                            .queryParam("data", item)
//                            .build().toUri(),
//                        3),
//                    batchPool)

                    CompletableFuture.supplyAsync(
                            () ->
//                                getWithRetry1(restTemplate, pdfUrl, item, 1),
                            {
                                // 1. 用 RestTemplate 的 exchange + ParameterizedTypeReference
                                ResponseEntity<List<String>> resp =
                                    restTemplate.exchange(
                                        pdfUrl + "?data=" + item,
                                        HttpMethod.GET,
                                        null,
                                        new ParameterizedTypeReference<List<String>>() {
                                        });
                                return resp.getBody();
                            },
                            batchPool)
                        .exceptionally(ex -> List.of())
            )
            .toList();

        List<String> pdfUrls = futures.stream()
            .map(CompletableFuture::join)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

        sw.stop();
        log.info("结果batch {} finished, size = {}, cost = {} ms", taskId, pdfUrls.size(), sw.getTime());
    }

    public static List<String> getWithRetry2(RestTemplate rt, String url, String query, int max) {
        int retry = 0;
        while (true) {
            try {
                retry++;
                return rt.exchange(url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<String>>() {
                    }).getBody();
            } catch (Exception ex) {
                if (retry < 3) {
                    throw new RuntimeException("Retry exhausted", ex);
                }
                LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(200 * (retry + 1))); // 退避
//                    Thread.sleep(1800);
            }
        }
    }

    public static List<String> getWithRetry1(RestTemplate rt, String url, String query, int max) {
        for (int i = 0; i < max; i++) {
            try {
                RequestEntity<Void> request = RequestEntity
                    .get(UriComponentsBuilder.fromHttpUrl(url)
                        .queryParam("data", query)
                        .build().toUri())
                    .build();
                ResponseEntity<List<String>> resp = rt.exchange(request, new ParameterizedTypeReference<List<String>>() {
                });
                System.out.println("结果代码：" + resp.getStatusCode() + " url:" + url);
                // 1. 成功：直接返回，不再重试
                if (resp.getStatusCode().is2xxSuccessful() && resp.getBody() != null) {
                    return resp.getBody();
                }

                // 2. 客户端错误（4xx）：业务问题，别再重试
                if (resp.getStatusCode().is4xxClientError()) {
                    throw new RuntimeException("Client error: " + resp.getStatusCode());
                }

                // 3. 服务器错误（5xx）：可重试
                if (resp.getStatusCode().is5xxServerError()) {
                    throw new HttpServerErrorException(resp.getStatusCode());
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (i == max - 1) throw new RuntimeException("Retry exhausted", e);
                LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(200 * (i + 1)));
            }
        }
        return List.of();
    }

    public static List<String> getWithRetry00(RestTemplate rt, URI uri, int max) {
        for (int i = 0; i < max; i++) {
            try {
                return rt.exchange(uri, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<String>>() {
                    }).getBody();
            } catch (Exception e) {
                System.out.println("异常url：" + uri.toString());
                e.printStackTrace();
                if (i == max - 1) throw new RuntimeException("Retry exhausted", e);
                LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(200));
            }
        }
        return List.of();
    }


//    @Async 是 Spring 的“声明式异步” 加了这个注解的方法，Spring 会把它包成 Runnable 扔进 TaskExecutor，调用者瞬间拿到一个空壳的 Future，原线程继续往下跑——方法自己就成了异步任务。
//    doBatchBean 本身并不是异步方法；它只是一个普通同步方法，只不过在方法内部用了 CompletableFuture 把子任务扔到了线程池里并行执行。调用者线程会一路执行到 future.join() 那一行才被阻塞，直到所有子任务结束。
//    CompletableFuture.supplyAsync(..., pool) 是“命令式异步” 你把一段代码显式地交给某个线程池，返回一个 CF 对象；调用线程仍然同步地往下跑，只不过它后面可以选择 join()/get() 去等结果，也可以选择注册回调继续干别的。
//    因此方法本身还是同步的，只是内部任务被并发执行了。

//“同步/异步”说的是调用风格（等不等结果立即返回）；
//        “阻塞/非阻塞”说的是线程状态（等的过程中有没有被操作系统挂起）。
    public void doBatchBean11(String taskId) {

        Supplier<List<String>> pdfTask = () -> {
            // 模拟耗时：调 REST、生成 PDF、IO 等等
            try {
                Thread.sleep(10_000);          // 故意睡 10 s，让超时生效
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // 别忘了复位中断标志
                throw new RuntimeException("interrupted", e);
            }
            return List.of("file1.pdf", "file2.pdf");
        };
        CompletableFuture<List<String>> fu = CompletableFuture.supplyAsync(pdfTask,batchPool)
            .orTimeout(60,TimeUnit.SECONDS)
            .exceptionally(ex -> List.of("default.pdf"));


        String pdfUrl = "http://localhost:8080/system/taskRecord/getPdfTest";
        List<String> dataList = new ArrayList<>();// ...; // 你的原始数据列表，例如 List<String>
        for (int i = 0; i < 1000; i++) {
            dataList.add("task id:" + i);
        }


        HttpComponentsClientHttpRequestFactory factory =
            new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(3_000);   // ① 连接建立超时
        factory.setReadTimeout(10_000);     // ② 读数据超时（就是 SO_TIMEOUT）
        factory.setConnectionRequestTimeout(2_000); // ③ 从连接池拿连接的超时
        RestTemplate restTemplateNew = new RestTemplate(factory);


        RestClient restClient = RestClient.builder()
            .requestFactory(factory)
            .build();
        String result = restClient.get().uri("").retrieve().body(String.class);



        List<CompletableFuture<List<String>>> futures = dataList.stream()
            .map(item -> CompletableFuture.supplyAsync(
                () -> {
                    // 1. 用 RestTemplate 的 exchange + ParameterizedTypeReference
                    ResponseEntity<List<String>> resp =
                        restTemplateNew.exchange(
                            pdfUrl + "?data=" + item,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<List<String>>() {
                            });
                    return resp.getBody();
                },
                batchPool).orTimeout(20,TimeUnit.SECONDS).exceptionally(ex-> ResponseEntity.ok(List.of("www")).getBody()))
            .toList();

        List<String> pdfUrls = futures.stream()
            .map(CompletableFuture::join)   // 先等每个批次完成  List<String>
            .flatMap(Collection::stream) //.flatMap(List::stream)          // 把批次里的元素摊平成 url 流
            .collect(Collectors.toList());  // 收集成最终的扁平列表


        System.out.println("打印最后的结果");
        pdfUrls.forEach(t -> {
            System.out.println("pdfUrl:" + t);
        });
    }
//        List<CompletableFuture<String>> futures =
//        dataList.stream().map(item->
//                CompletableFuture.supplyAsync(()->restTemplate.getForObject(pdfUrl + "?data=task"+taskId+" condition:"+item,String.class),executor)
//            ).toList();
//

//        RestTemplate restTemplate = new RestTemplate();
//        int concurrency = 50;
//        ExecutorService batchPool = Executors.newFixedThreadPool(concurrency);


//干脆不要自己的 Bean，直接用 Spring 现成的
//把 private final Executor batchPool; 改成
//    @Bean("batchPool")
//    public Executor batchPool() {
//        ThreadPoolTaskExecutor exec = new ThreadPoolTaskExecutor();
//        exec.setCorePoolSize(50);
//        exec.setMaxPoolSize(50);
//        exec.setQueueCapacity(1_000);
//        exec.setThreadNamePrefix("batch-");
//        exec.setWaitForTasksToCompleteOnShutdown(true);
//        exec.setAwaitTerminationSeconds(60);
//        exec.initialize();
//        return exec;
//    }

    //    @Bean("restTemplate")
//    @Primary   // 或者 @Qualifier("restTemplate")
    public RestTemplate restTemplate() {
        /*

        // 1. 连接池
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(50);

        // 2. 超时
        RequestConfig rc = RequestConfig.custom()
            .setConnectTimeout(3_000)
            .setSocketTimeout(10_000)
            .build();

//        CloseableHttpClient httpClient = HttpClients.custom()
//            .setConnectionManager(cm)
//            .setDefaultRequestConfig(rc)
//            .build();

        CloseableHttpClient httpClient = HttpClients.custom()
            .setConnectionManager(cm)
            .setDefaultRequestConfig(rc)
            // 可选：.setRetryHandler(new DefaultHttpRequestRetryHandler(2, true))
            .build();

        HttpComponentsClientHttpRequestFactory factory =
            new HttpComponentsClientHttpRequestFactory(httpClient);
        // 3. 工厂
//        HttpComponentsClientHttpRequestFactory factory =
//            new HttpComponentsClientHttpRequestFactory(httpClient);

        return new RestTemplate(factory);

        */

        return new RestTemplate();
    }


    public void doBatch(String taskId) {
//        HttpComponentsClientHttpRequestFactory factory =
//            new HttpComponentsClientHttpRequestFactory();
//        factory.setConnectTimeout(3_000);
//        factory.setReadTimeout(10_000);
//        factory.setConnectionRequestTimeout(3_000);
//
//        PoolingHttpClientConnectionManager cm =
//            new PoolingHttpClientConnectionManager();
//        cm.setMaxTotal(200);               // 总连接
//        cm.setDefaultMaxPerRoute(50);      // 到本机单域名
//        CloseableHttpClient httpClient =
//            HttpClients.custom().setConnectionManager(cm).build();
//        factory.setHttpClient(httpClient);
//
//        RestTemplate rt = new RestTemplate(factory);
//

        RestTemplate rt = new RestTemplate();
        String pdfUrl = "http://localhost:8080/system/taskRecord/getPdfTest";
        int concurrency = 50;
        ExecutorService batchPool = Executors.newFixedThreadPool(concurrency);//并发度由 batchPool 的线程数决定（50）
        //10_000 只是“任务量”，不是“并发量”；
        List<CompletableFuture<String>> futures = IntStream.range(0, 10_000) //range(0, 10_000) 生成的是 0,1,2 … 9999 → 10 000 个 int。
            .mapToObj(i -> CompletableFuture.supplyAsync(
                () -> rt.getForObject(pdfUrl + "?data=task" + taskId + " condition:" + i, String.class),
                batchPool))
            .toList();

        List<String> pdfUrls = futures.stream()
            .map(CompletableFuture::join)
            .collect(Collectors.toList());//主线程在这里阻塞，直到 10 000 个 future 全部完成；

        System.out.println("打印最后的结果");
        pdfUrls.forEach(t -> {
            System.out.println("pdfUrl:" + t);
        });


//        List<CompletableFuture<Path>> futures = IntStream.range(0,10_000)
//            .mapToObj(i -> CompletableFuture.supplyAsync(
//                () -> downloadPdf(i),        // 用带池的 RestTemplate
//                batchPool))                  // 50 线程
//            .toList();
//
//        List<Path> pdfs = futures.stream()
//            .map(CompletableFuture::join)
//            .collect(Collectors.toList());
//
//        Path zip = packZip(pdfs);            // 打成 zip
//        // 3. 结果存到临时目录 / MinIO / S3
//        Files.move(zip, Paths.get("/tmp/results/"+taskId+".zip"));
//        // 4. 写 Redis 标记“完成”
//        redisTemplate.opsForValue().set("batch:"+taskId, "ok", Duration.ofHours(1));
    }
}
