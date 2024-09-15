package com.two_ddang.logistics.delivery.domain.vo;

import com.two_ddang.logistics.core.entity.TransitStatus;
import com.two_ddang.logistics.delivery.domain.model.Delivery;
import com.two_ddang.logistics.delivery.domain.model.DeliveryAgent;
import com.two_ddang.logistics.delivery.domain.model.Transit;
import com.two_ddang.logistics.delivery.domain.model.TransitRoute;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TransitRouteVO {


    private final UUID id;
    private final UUID deliveryId;
    private final UUID transitId;
    private final DeliveryAgentVO transitAgent;
    private final int sequence;
    private final TransitStatus transitStatus;
    private final UUID departmentHubId;
    private final UUID arriveHubId;
    private final String route;
    private final int estimateDistance;
    private final int estimateTime;
    private final int actualDistance;
    private final int actualTime;
    private LocalDateTime departmentAt;
    private LocalDateTime arriveAt;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final LocalDateTime deletedAt;
    private final Integer createdBy;
    private final Integer updatedBy;
    private final Integer deletedBy;
    private final boolean isDeleted;


    public static TransitRouteVO fromEntity(TransitRoute transitRoute) {

        transitRoute = Objects.isNull(transitRoute) ? TransitRoute.empty() : transitRoute;
        Delivery delivery = Objects.isNull(transitRoute.getDelivery()) ? Delivery.empty() : transitRoute.getDelivery();
        Transit transit = Objects.isNull(transitRoute.getTransit()) ? Transit.empty() : transitRoute.getTransit();
        DeliveryAgent agent = Objects.isNull(transitRoute.getTransitAgent()) ? DeliveryAgent.empty() : transitRoute.getTransitAgent();

        return new TransitRouteVO(
                transitRoute.getId(), delivery.getId(), transit.getId(),
                DeliveryAgentVO.fromEntity(agent), transitRoute.getSequence(), transitRoute.getTransitStatus(),
                transitRoute.getDepartmentHubId(), transitRoute.getArriveHubId(), transitRoute.getRoute(),
                transitRoute.getEstimateDistance(), transitRoute.getEstimateTime(), transitRoute.getActualDistance(), transitRoute.getActualTime(),
                transitRoute.getDepartmentAt(), transitRoute.getArriveAt(),
                transitRoute.getCreatedAt(), transitRoute.getUpdatedAt(), transitRoute.getDeletedAt(), transitRoute.getCreatedBy(), transitRoute.getUpdatedBy(), transitRoute.getDeletedBy(), transitRoute.isDeleted()

        );

    }

}
