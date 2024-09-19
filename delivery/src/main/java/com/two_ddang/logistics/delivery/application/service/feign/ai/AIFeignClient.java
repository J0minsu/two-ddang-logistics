package com.two_ddang.logistics.delivery.application.service.feign.ai;

import com.two_ddang.logistics.delivery.application.service.feign.hub.HubService;
import com.two_ddang.logistics.delivery.application.service.feign.hub.dto.req.HubRouteModifyRequest;
import com.two_ddang.logistics.delivery.application.service.feign.hub.fallback.HubFallbackFactory;
import com.two_ddang.logistics.delivery.infrastructrure.configuration.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(
        name = "ai",
configuration = FeignClientConfig.class,
fallbackFactory = HubFallbackFactory.class)
public interface AIFeignClient extends AIService {

    @Override
    default void requestRoutes(List<HubRouteModifyRequest> routes) {

    }
}
