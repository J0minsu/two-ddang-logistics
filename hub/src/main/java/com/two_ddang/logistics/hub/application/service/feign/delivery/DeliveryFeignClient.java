package com.two_ddang.logistics.hub.application.service.feign.delivery;

import com.two_ddang.logistics.hub.application.service.feign.delivery.fallback.DeliveryFallbackFactory;
import com.two_ddang.logistics.hub.infrastructrure.configuration.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "delivery",
configuration = FeignClientConfig.class,
fallbackFactory = DeliveryFallbackFactory.class)
public interface DeliveryFeignClient extends DeliveryService {

}
