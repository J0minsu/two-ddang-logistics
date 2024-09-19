package com.two_ddang.logistics.delivery.application.service.feign.ai;

import com.two_ddang.logistics.core.util.ResponseDTO;
import com.two_ddang.logistics.delivery.application.service.feign.ai.dto.req.RecommendTransitRouteRequest;
import com.two_ddang.logistics.delivery.application.service.feign.ai.dto.res.RecommendTransitRouteResponse;
import com.two_ddang.logistics.delivery.application.service.feign.hub.dto.req.HubRouteModifyRequest;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface AIService {

    ResponseDTO<RecommendTransitRouteResponse> recommendRoute(Map<UUID, RecommendTransitRouteRequest> request);

}
