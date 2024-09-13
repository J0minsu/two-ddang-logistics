package com.two_ddang.logistics.delivery.domain.vo;

import com.two_ddang.logistics.core.entity.TransitStatus;
import com.two_ddang.logistics.delivery.domain.model.DeliveryAgent;
import com.two_ddang.logistics.delivery.domain.model.Transit;
import com.two_ddang.logistics.delivery.domain.model.TransitRoute;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TransitVO {

    private final UUID id;
    private final DeliveryAgentVO transitAgent;
    private final int totalEstimateDistance;
    private final TransitStatus transitStatus;
    private final int totalStopCount = 0;
    private final int arrivedStopCount = 0;
    private final List<TransitRouteVO> transitRoutes;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final LocalDateTime deletedAt;
    private final Integer createdBy;
    private final Integer updatedBy;
    private final Integer deletedBy;
    private final boolean isDeleted;

    public static TransitVO fromEntity(Transit transit) {
        return new TransitVO(
                transit.getId(), DeliveryAgentVO.fromEntity(transit.getTransitAgent()), transit.getTotalEstimateDistance(),
                transit.getTransitStatus(), transit.getTransitRoutes().stream().map(TransitRouteVO::fromEntity).toList(),
                transit.getCreatedAt(), transit.getUpdatedAt(), transit.getDeletedAt(), transit.getCreatedBy(), transit.getUpdatedBy(), transit.getDeletedBy(), transit.isDeleted()
        );
    }

}
