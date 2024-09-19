package com.two_ddang.logistics.delivery.application.service.feign.ai;

import com.two_ddang.logistics.core.util.ResponseDTO;
import com.two_ddang.logistics.delivery.application.service.feign.ai.dto.req.RecommendTransitRouteRequest;
import com.two_ddang.logistics.delivery.application.service.feign.ai.dto.res.RecommendTransitRouteResponse;
import com.two_ddang.logistics.delivery.application.service.feign.ai.fallback.AIFallbackFactory;
import com.two_ddang.logistics.delivery.application.service.feign.hub.HubService;
import com.two_ddang.logistics.delivery.application.service.feign.hub.dto.req.HubRouteModifyRequest;
import com.two_ddang.logistics.delivery.application.service.feign.hub.fallback.HubFallbackFactory;
import com.two_ddang.logistics.delivery.infrastructrure.configuration.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@FeignClient(
        name = "ai",
configuration = FeignClientConfig.class,
fallbackFactory = AIFallbackFactory.class)
public interface AIFeignClient extends AIService {

    @Override
    @PostMapping("/api/v1/routes")
    ResponseDTO<RecommendTransitRouteResponse> recommendRoute(@RequestBody Map<UUID, RecommendTransitRouteRequest> request);
}
