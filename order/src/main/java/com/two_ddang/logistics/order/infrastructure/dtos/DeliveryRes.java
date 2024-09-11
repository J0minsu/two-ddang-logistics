package com.two_ddang.logistics.order.infrastructure.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.two_ddang.logistics.core.entity.DeliveryStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
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

    public DeliveryRes(UUID deliveryId) {
        this.deliveryId = deliveryId;
    }
}
