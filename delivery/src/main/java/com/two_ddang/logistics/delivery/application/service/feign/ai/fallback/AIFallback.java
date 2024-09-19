package com.two_ddang.logistics.delivery.application.service.feign.ai.fallback;

import com.two_ddang.logistics.core.util.ResponseDTO;
import com.two_ddang.logistics.delivery.application.service.feign.ai.AIFeignClient;
import com.two_ddang.logistics.delivery.application.service.feign.ai.dto.req.RecommendTransitRouteRequest;
import com.two_ddang.logistics.delivery.application.service.feign.ai.dto.res.RecommendTransitRouteResponse;
import com.two_ddang.logistics.delivery.application.service.feign.hub.HubFeignClient;
import com.two_ddang.logistics.delivery.application.service.feign.hub.dto.req.HubRouteModifyRequest;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class AIFallback implements AIFeignClient {

    private final Throwable cause;

    public AIFallback(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public ResponseDTO<RecommendTransitRouteResponse> recommendRoute(RecommendTransitRouteRequest request) {
        return null;
    }
}
