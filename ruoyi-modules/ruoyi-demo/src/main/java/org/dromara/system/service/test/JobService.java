package org.dromara.system.service.test;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.dromara.system.domain.SysTaskRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class JobService {
    @Resource(name = "scheduledExecutorService")
    private ScheduledExecutorService schedulePool;
    @Autowired
    private AsyncService asyncService;
    @PostConstruct
    public void init() {
        // 每 30 s 执行一次
        schedulePool.scheduleWithFixedDelay(this::clean, 0, 30, TimeUnit.SECONDS);
        SysTaskRecord sysTaskRecord = new SysTaskRecord();
        sysTaskRecord.setId(100L);
        asyncService.saveOrder(sysTaskRecord);
    }

    private void clean() { log.info("每隔一会定时清理"); }
}
