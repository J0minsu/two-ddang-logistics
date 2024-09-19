package com.two_ddang.logistics.ai.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendTransitRouteResponse {

    //sequence, route-info
    Map<Integer, TransitRouteResponse> routes;

}
