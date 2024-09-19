package com.two_ddang.logistics.delivery.application.service.feign.ai;

import com.two_ddang.logistics.core.util.ResponseDTO;
import com.two_ddang.logistics.delivery.application.service.feign.ai.dto.req.RecommendTransitRouteRequest;
import com.two_ddang.logistics.delivery.application.service.feign.ai.dto.res.RecommendTransitRouteResponse;
import com.two_ddang.logistics.delivery.application.service.feign.hub.dto.req.HubRouteModifyRequest;

import java.util.List;

public interface AIService {

    ResponseDTO<RecommendTransitRouteResponse> recommendRoute(RecommendTransitRouteRequest request);

}
