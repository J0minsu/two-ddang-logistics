package com.two_ddang.logistics.delivery.application.service.feign.ai.fallback;

import com.two_ddang.logistics.delivery.application.service.feign.hub.fallback.HubFallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AIFallbackFactory implements FallbackFactory<AIFallback> {

    @Override
    public AIFallback create(Throwable cause) {
        return null;
    }
}
