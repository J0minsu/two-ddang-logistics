package com.two_ddang.logistics.hub.application.service.feign.transit;

import com.two_ddang.logistics.hub.application.service.feign.transit.fallback.TransitFallbackFactory;
import com.two_ddang.logistics.hub.infrastructrure.configuration.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "transit",
configuration = FeignClientConfig.class,
fallbackFactory = TransitFallbackFactory.class)
public interface TransitFeignClient extends TransitService {

}
