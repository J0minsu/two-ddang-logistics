package com.two_ddang.logistics.ai.application.service.feign.delivery.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

@Slf4j
public class DeliveryFallbackFactory implements FallbackFactory<DeliveryFallback> {

    @Override
    public DeliveryFallback create(Throwable cause) {

        log.info(cause.toString(), "AI에서 Delivery를 호출할 때 오류가 발생했습니다.");

        return null;
    }

}
