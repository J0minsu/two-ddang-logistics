package com.two_ddang.logistics.delivery.domain.vo;

import com.two_ddang.logistics.core.entity.DeliveryStatus;
import com.two_ddang.logistics.delivery.domain.model.Delivery;
import com.two_ddang.logistics.delivery.domain.model.DeliveryAgent;
import com.two_ddang.logistics.delivery.domain.model.TransitRoute;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class DeliveryVO {

    private final UUID id;
    private final UUID orderId;
    private final DeliveryStatus deliveryStatus;
    private final UUID departmentHubId;
    private final UUID arriveHubId;
    private final String deliveryAddress;
    private final UUID receiveCompanyId;
    private final DeliveryAgentVO deliveryAgent;
    private final TransitRouteVO transitRoute;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final LocalDateTime deletedAt;
    private final Integer createdBy;
    private final Integer updatedBy;
    private final Integer deletedBy;
    private final boolean isDeleted;

    public static DeliveryVO fromEntity(Delivery delivery) {

        delivery = Objects.isNull(delivery) ? Delivery.empty() : delivery;

        DeliveryAgentVO deliveryAgentVO = DeliveryAgentVO.fromEntity(
                Optional.ofNullable(delivery.getDeliveryAgent()).orElse(DeliveryAgent.empty())
        );

        TransitRouteVO transitRouteVO = TransitRouteVO.fromEntity(
                Optional.ofNullable(delivery.getTransitRoute()).orElse(TransitRoute.empty())
        );

        return new DeliveryVO(
                delivery.getId(), delivery.getOrderId(), delivery.getDeliveryStatus(),
                delivery.getDepartmentHubId(), delivery.getArriveHubId(), delivery.getDeliveryAddress(), delivery.getReceiveCompanyId(),
                deliveryAgentVO, transitRouteVO,
                delivery.getCreatedAt(), delivery.getUpdatedAt(), delivery.getDeletedAt(), delivery.getCreatedBy(), delivery.getUpdatedBy(), delivery.getDeletedBy(), delivery.isDeleted()
        );

    }

}
