package org.dromara.system.service.test;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.dromara.system.domain.SysTaskRecord;
import org.dromara.system.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Slf4j
@Service
public class AsyncService {
    @Resource(name = "asyncTaskExecutor")
    private ThreadPoolTaskExecutor asyncPool;
    @Resource(name = "virtualThreadExecutor")
    private Executor virtualThreadExecutor;
    @Autowired
    private ApiService apiService;


    public void saveOrder(SysTaskRecord o) {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            CompletableFuture.supplyAsync(() -> {
                String r = apiService.fetchData("http://localhost:8080/system/taskRecord/getPdfTest?data=" + finalI + "testPoolasyncTaskExecutor");
                return r;
            }, asyncPool);

            CompletableFuture.supplyAsync(() -> {
                String r = apiService.fetchData("http://localhost:8080/system/taskRecord/getPdfTest?data=" + finalI + "testPoolvirtualThreadExecutor");
                return r;
            }, virtualThreadExecutor);
        }
    }
}
