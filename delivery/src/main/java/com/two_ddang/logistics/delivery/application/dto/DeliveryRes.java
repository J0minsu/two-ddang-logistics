package com.two_ddang.logistics.delivery.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.two_ddang.logistics.core.entity.DeliveryStatus;
import com.two_ddang.logistics.delivery.domain.model.Delivery;
import com.two_ddang.logistics.delivery.domain.vo.DeliveryVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Schema(title = " DTO")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryRes {

    private UUID deliveryId;
    private UUID deliveryAgentId;
    private UUID orderId;
    private UUID departHubId;
    private UUID arrivedHubId;
    private String deliveryAddress;
    private UUID receiveCompanyId;
    private DeliveryStatus deliveryStatus;
    private LocalDateTime createdAt;

    public static DeliveryRes fromVO(DeliveryVO delivery) {

        return new DeliveryRes(
                delivery.getId(), delivery.getDeliveryAgent().getId(), delivery.getOrderId(), delivery.getDepartmentHubId(), delivery.getArriveHubId(),
                delivery.getDeliveryAddress(), delivery.getReceiveCompanyId(), delivery.getDeliveryStatus(), delivery.getCreatedAt());

    }

    public static DeliveryRes example(boolean isNotStartDelivery) {

        UUID randomUUID = UUID.randomUUID();

        return new DeliveryRes(
                randomUUID, isNotStartDelivery ? null : randomUUID, randomUUID, randomUUID, randomUUID,
                "서울시 동작구", randomUUID, DeliveryStatus.WAITING, LocalDateTime.now());

    }

}
