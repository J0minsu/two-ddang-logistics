package com.two_ddang.logistics.hub.application.service.feign.transit.fallback;

import com.two_ddang.logistics.hub.application.service.feign.transit.TransitFeignClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TransitFallback implements TransitFeignClient {

    private final Throwable cause;

    public TransitFallback(Throwable cause) {
        this.cause = cause;
    }


}
