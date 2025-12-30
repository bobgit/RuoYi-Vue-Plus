package org.dromara.common.core.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 线程池 配置属性
 *
 * @author Lion Li
 */
@Data
@ConfigurationProperties(prefix = "thread-pool")// 只负责绑定配置文件
//如果没有任何 thread-pool.* 键，则字段保持默认值（int 默认 0，Integer 默认 null，你自己 @Data 里给的初始值等）。
//总之不会因为“没写配置”就不创建这个 Bean。
public class ThreadPoolProperties {

    /**
     * 是否开启线程池
     */
    private boolean enabled = true;

    /**
     * 队列最大长度
     */
    private int queueCapacity= 1024;

    /**
     * 线程池维护线程所允许的空闲时间
     */
    private int keepAliveSeconds = 60;

    /**
     * 线程池维护线程所允许的空闲时间
     */
    private int corePoolSize = 8;
    private int maxPoolSize = 16;
}
//
//thread-pool:
//enabled: true
//core-pool-size: 8
//max-pool-size: 16
//queue-capacity: 1024
//keep-alive-seconds: 60
