package com.two_ddang.logistics.delivery.application.dto;


import com.two_ddang.logistics.core.entity.TransitStatus;
import com.two_ddang.logistics.delivery.domain.vo.DeliveryAgentVO;
import com.two_ddang.logistics.delivery.domain.vo.TransitRouteVO;
import com.two_ddang.logistics.delivery.domain.vo.TransitVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
@Schema(title = "운송 응답 DTO")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TransitRes {

    private UUID transitId;
    private UUID transitAgentId;
    private UUID departmentHubId;
    private TransitStatus transitStatus;
    private int totalCount;
    private int stayCount;
    private Map<Integer, List<TransitRouteRes>> route;

    public static TransitRes fromEntity(TransitVO transit) {

        Map<Integer, List<TransitRouteRes>> routes = transit.getTransitRoutes().stream()
                .map(TransitRouteRes::fromEntity)
                .collect(Collectors.groupingBy(TransitRouteRes::getSequence));

        TransitRes transitRes = new TransitRes(
                transit.getId(), transit.getTransitAgent().getId(),
                transit.getTransitRoutes().get(0).getDepartmentHubId(),
                transit.getTransitStatus(), routes.size(), 0, routes);

        return transitRes;
    };

}
