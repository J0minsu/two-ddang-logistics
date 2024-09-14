package com.two_ddang.logistics.hub.application.service.feign.delivery.fallback;

import com.two_ddang.logistics.hub.application.service.feign.delivery.DeliveryFeignClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeliveryFallback implements DeliveryFeignClient {

    private final Throwable cause;

    public DeliveryFallback(Throwable cause) {
        this.cause = cause;
    }


}
