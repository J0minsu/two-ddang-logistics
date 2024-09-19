package com.two_ddang.logistics.delivery.application.service.feign.ai.dto.req;

import com.two_ddang.logistics.delivery.domain.model.TransitRoute;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendTransitRouteRequest {

    private List<TransitRouteRequest> routes;

}

