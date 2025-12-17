// src/main/java/com/example/demo/ExternalServiceClient.java
package org.dromara.system.service;

import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import java.util.Random;

@Component
@Slf4j
public class ExternalServiceClient {

    private final Random random = new Random();

    /**
     * 模拟调用外部服务
     * @param data 要发送的数据
     * @return boolean 是否成功
     */
    public boolean call(String data) {
        log.info("【外部服务】正在调用，数据: {}...", data);
        // 模拟70%的失败率，以便观察重试
        if (random.nextInt(10) < 7) {
            log.error("【外部服务】调用失败！");
            throw new RuntimeException("模拟外部服务不可用");
        }
        log.info("【外部服务】调用成功！");
        return true;
    }
}
