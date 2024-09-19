package com.two_ddang.logistics.ai.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendTransitRouteRequest {

    private List<TransitRouteRequest> routes;

}
