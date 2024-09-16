package com.two_ddang.logistics.delivery.domain.vo;

import com.two_ddang.logistics.core.entity.TransitStatus;
import com.two_ddang.logistics.delivery.domain.model.TransitRoute;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TransitRouteVO {


    private final UUID id;
    private final DeliveryVO delivery;
    private final TransitVO transit;
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

        return new TransitRouteVO(
                transitRoute.getId(), DeliveryVO.fromEntity(transitRoute.getDelivery()), TransitVO.fromEntity(transitRoute.getTransit()),
                DeliveryAgentVO.fromEntity(transitRoute.getTransitAgent()), transitRoute.getSequence(), transitRoute.getTransitStatus(),
                transitRoute.getDepartmentHubId(), transitRoute.getArriveHubId(), transitRoute.getRoute(),
                transitRoute.getEstimateDistance(), transitRoute.getEstimateTime(), transitRoute.getActualDistance(), transitRoute.getActualTime(),
                transitRoute.getDepartmentAt(), transitRoute.getArriveAt(),
                transitRoute.getCreatedAt(), transitRoute.getUpdatedAt(), transitRoute.getDeletedAt(), transitRoute.getCreatedBy(), transitRoute.getUpdatedBy(), transitRoute.getDeletedBy(), transitRoute.isDeleted()

        );

    }

}
