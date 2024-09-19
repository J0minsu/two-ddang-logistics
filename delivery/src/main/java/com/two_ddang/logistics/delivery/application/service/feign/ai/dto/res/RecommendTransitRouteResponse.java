package com.two_ddang.logistics.delivery.application.service.feign.ai.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendTransitRouteResponse {

    //sequence, route-info
    Map<Integer, TransitRouteResponse> routes;

}

