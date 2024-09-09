package com.two_ddang.logistics.delivery.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.two_ddang.logistics.delivery.domain.model.DeliveryStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

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

    public static DeliveryRes fromVO(
            UUID deliveryId, UUID deliveryAgentId, UUID orderId, UUID departHubId, UUID arrivedHubId,
            String deliveryAddress, UUID receiveCompanyId, DeliveryStatus deliveryStatus, LocalDateTime createdAt) {

        return new DeliveryRes(
                deliveryId, deliveryAgentId, orderId, departHubId, arrivedHubId,
                deliveryAddress, receiveCompanyId, deliveryStatus, createdAt);

    }

    public static DeliveryRes example(boolean isNotStartDelivery) {

        UUID randomUUID = UUID.randomUUID();

        return new DeliveryRes(
                randomUUID, isNotStartDelivery ? null : randomUUID, randomUUID, randomUUID, randomUUID,
                "서울시 동작구", randomUUID, DeliveryStatus.WAITING, LocalDateTime.now());

    }

}
