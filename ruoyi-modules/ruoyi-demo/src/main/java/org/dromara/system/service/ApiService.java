// ApiService.java
package org.dromara.system.service;


import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    private final RestTemplate restTemplate;

    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 调用外部 API，支持重试
     */
    @Retryable(
        value = {
            ResourceAccessException.class,      // 网络/超时异常
            ExternalServiceException.class     // 业务自定义异常（来自 errorHandler）
        },
        maxAttempts = 8,
        backoff = @Backoff(delay = 2000, multiplier = 2) // 2s, 4s, 8s
    )
    public String fetchData(String url) {
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (HttpStatusCodeException ex) {
            // 将 4xx/5xx 转为自定义异常，便于重试和日志
            throw new ExternalServiceException(
                "API returned " + ex.getStatusCode() + ": " + ex.getResponseBodyAsString(),
                ex.getStatusCode().value()
            );
        }
    }

    @Recover
    public String recover(Exception ex, String url) {
        // 可记录日志、返回兜底值、或抛出最终异常
        throw new IllegalStateException("Failed to call external service after retries: " + url, ex);
    }
}
