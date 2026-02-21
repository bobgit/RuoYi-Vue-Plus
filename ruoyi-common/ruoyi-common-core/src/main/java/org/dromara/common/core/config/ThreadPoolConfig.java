package org.dromara.common.core.config;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.dromara.common.core.config.properties.ThreadPoolProperties;
import org.dromara.common.core.utils.SpringUtils;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.VirtualThreadTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池配置
 *
 * @author Lion Li
 **/
@Slf4j
@AutoConfiguration  //配置类能否注册 → 只看你有没有加 @Configuration/@AutoConfiguration
@EnableConfigurationProperties(ThreadPoolProperties.class)
//把“纯 POJO”的 @ConfigurationProperties 类注册成 Spring 容器里的 Bean ，并且完成属性绑定（application.yml / application.properties → POJO）。
public class ThreadPoolConfig {

    /**
     * 核心线程数 = cpu 核心数 + 1
     */
//    private final int core = Runtime.getRuntime().availableProcessors() + 1;

    // 成员变量用于优雅关闭
    //业务需要“定时/周期”能力，ScheduledThreadPoolExecutor 是 JUC 原生支持“周期性/延迟”调度 的线程池实现，提供了 schedule()、scheduleAtFixedRate()、scheduleWithFixedDelay() 等 API。
    //ThreadPoolTaskExecutor 是 Spring 对普通 ThreadPoolExecutor 的封装，只能做异步执行，不能定时/周期执行。
    private ScheduledExecutorService scheduledExecutorService;
    private AsyncTaskExecutor virtualThreadExecutor;


    // ========================
    // 1. 定时/周期任务线程池（Scheduled）
    // ========================
    @Bean(name = "scheduledExecutorService",destroyMethod = "shutdown")
    protected ScheduledExecutorService scheduledExecutorService(ThreadPoolProperties props) {
        if(!props.isEnabled()){
            log.warn("⚠️ 定时线程池被禁用，退化为单线程模式");
            return Executors.newSingleThreadScheduledExecutor(r -> {
                Thread t = new Thread(r, "scheduled-degraded-" + new AtomicInteger(1).getAndIncrement());
                t.setDaemon(true);
                return t;
            });
        }
        int core = Runtime.getRuntime().availableProcessors() + 1;
        // daemon 必须为 true 守护线程：不会阻止 JVM 退出，但会被“硬杀”，只能跑可丢弃任务。
        BasicThreadFactory.Builder builder = new BasicThreadFactory.Builder().daemon(true);//JVM 正常退出条件：当且仅当所有 非守护线程 全部结束；守护线程剩多少都无所谓。守护线程被强行终止时不会执行 finally、也不会跑 uncaughtExceptionHandler，直接死掉。
        if (SpringUtils.isVirtual()) {
            builder.namingPattern("virtual-schedule-pool-%d").wrappedFactory(new VirtualThreadTaskExecutor().getVirtualThreadFactory());
        } else {
            builder.namingPattern("schedule-pool-%d");
        }
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(core,
            builder.build(),
            new ThreadPoolExecutor.CallerRunsPolicy()) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                printException(r, t);
            }
        };
        scheduledThreadPoolExecutor.setRemoveOnCancelPolicy(true);
        this.scheduledExecutorService = scheduledThreadPoolExecutor;
        return scheduledThreadPoolExecutor;
    }

    // ========================
    // 2. 通用异步任务线程池（基于配置）
    // ========================
    // 注意：ThreadPoolTaskExecutor 实现了 Executor、ExecutorService、AsyncTaskExecutor，所以注入时你可以用任意这些类型。
    //private Executor asyncTaskExecutor; // ✅ OK private AsyncTaskExecutor asyncTaskExecutor; /
    @Bean(name = "asyncTaskExecutor")
    public ThreadPoolTaskExecutor asyncTaskExecutor(ThreadPoolProperties props) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor() {
//            @Override
//            protected ExecutorService initializeExecutor(
//                ThreadFactory threadFactory,
//                RejectedExecutionHandler rejectedExecutionHandler) {
//                if (!props.isEnabled()) {
//                    log.warn("⚠️ 异步线程池被禁用，退化为同步执行");
//                    return new DegradedExecutorService();
//                }
//                return super.initializeExecutor(threadFactory, rejectedExecutionHandler);
//            }
//        };
        if(props.isEnabled()){
            executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        }else{
            executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                    r.run();// 直接在调用线程执行
                }
            });
        }

        // 配置为"退化"模式
        executor.setCorePoolSize(props.isEnabled() ? props.getCorePoolSize(): 0);
        executor.setMaxPoolSize(props.isEnabled() ? props.getMaxPoolSize() : 0);
        executor.setQueueCapacity(props.isEnabled() ? props.getQueueCapacity() : 0);
//        executor.setKeepAliveSeconds();
        executor.setThreadNamePrefix(props.isEnabled() ? "asyncTask-" : "async-degraded-");

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);  // 优雅关闭
        executor.setAwaitTerminationSeconds(60);

        executor.initialize();
        return executor;
    }



    // ========================
    // 5. 虚拟线程执行器（JDK 21+）
    // ========================
    @Bean(name = "virtualThreadExecutor", destroyMethod = "")
    public Executor virtualThreadExecutor(ThreadPoolProperties props) {
        if (!SpringUtils.isVirtual()||!props.isEnabled()) {
            log.debug("当前 JDK 版本不支持虚拟线程，跳过创建 virtualThreadExecutor");
            log.warn("⚠️ 虚拟线程不可用，退化为平台线程");
            return Executors.newWorkStealingPool(2);
        }
        VirtualThreadTaskExecutor executor = new VirtualThreadTaskExecutor();
        log.info("✅ 初始化虚拟线程执行器");
        this.virtualThreadExecutor = executor;
        return executor;
    }

    /**
     * 销毁事件
     * 停止线程池
     * 先使用shutdown, 停止接收新任务并尝试完成所有已存在任务.
     * 如果超时, 则调用shutdownNow, 取消在workQueue中Pending的任务,并中断所有阻塞函数.
     * 如果仍然超時，則強制退出.
     * 另对在shutdown时线程本身被调用中断做了处理.
     */
    // 1. 容器回调标记，当容器准备把当前 Bean 销毁时（Spring 容器关闭、应用下线、热替换等），会自动帮你调用被 @PreDestroy 注解的方法；程序员不用手动去调。
//    因此它在这里的唯一作用就是：保证 JVM 退出或应用停服时，这段“优雅停线程池”的代码一定会被执行一次。
    //@PreDestroy 只是保证应用下线时这段流程一定会被调用，无需你手动执行。
    @PreDestroy
    public void destroy() {
        log.info("⏳ 正在关闭所有线程池...");
        shutdownExecutor(scheduledExecutorService, "scheduledExecutorService");
        shutdownExecutor((ExecutorService) virtualThreadExecutor, "virtualThreadExecutor");
        log.info("✅ 所有线程池已关闭");
    }

    public void shutdownExecutor(ExecutorService pool, String name) {
        try {
            if (pool != null && !pool.isShutdown()) {   // 2. 只要池子还在运行
                pool.shutdown();                        // 3. 第一步：优雅关闭
//                                                            – 不再接受新任务；
//                                                            – 已提交到队列里但还没开始跑的（Pending）任务仍然会被执行；
//                                                            – 已经在跑的线程不会收到 interrupt。
                try {
                    // 4. 最多等 120 s，等正在跑的任务自己完
//                                        – 阻塞当前线程，直到池子真正 TERMINATED 或者超时；
//                                        – 返回 true=已终止，false=超时。
                    if (!pool.awaitTermination(120, TimeUnit.SECONDS)) {
                        // 5. 超时还有任务没干完 → 强制阶段
//                            – 把队列里还没开始跑的任务列表返回给你（代码里没接返回值，直接丢弃）；
//                            – 给每一个正在干活的线程发 Thread.interrupt()；
//                            – 如果任务里有响应中断的逻辑（如 sleep、wait、BlockingQueue.take 等）就会抛 InterruptedException 从而提前退出；
//                            – 如果任务里吞了中断或者没阻塞，线程仍可能继续跑，所以代码里又等了第二遍 120 s。
                        pool.shutdownNow();           // 5.1 发 发中断interrupt 给所有线程
                        // 5.2 再给它 120 s 做收尾（日志、清理等）
                        if (!pool.awaitTermination(120, TimeUnit.SECONDS)) {
                            log.info("Pool did not terminate");
                            // 5.3 如果还是关不掉，记录日志，之后随 JVM 强制退出
                        }
                    }
                } catch (InterruptedException ie) {     // 6. 在等的过程中“别人”把当前线程 interrupt 了
                    pool.shutdownNow();                 // 6.1 立即强制
                    Thread.currentThread().interrupt(); // 6.2 把中断标记重新打回，让外层感知
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);               // 7. 兜底，任何异常都记录
        }
    }

    /**
     * 打印线程异常信息
     */
    public static void printException(Runnable r, Throwable t) {
        if (t == null && r instanceof Future<?>) {
            try {
                Future<?> future = (Future<?>) r;
                if (future.isDone()) {
                    future.get();
                }
            } catch (CancellationException ce) {
                t = ce;
            } catch (ExecutionException ee) {
                t = ee.getCause();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
        if (t != null) {
            log.error(t.getMessage(), t);
        }
    }

}
/*


public static void main(String[] args) {
    Thread t = new Thread(() -> {
        try {
            TimeUnit.SECONDS.sleep(5);
            System.out.println("daemon over");
        } finally {
            System.out.println("finally run");   // 若设 daemon=true，这一行不会打印
        }
    });
    t.setDaemon(true);   // 改成 false 试试，JVM 会等 5 s 后退出
    t.start();
    System.out.println("main over");
}

*/
