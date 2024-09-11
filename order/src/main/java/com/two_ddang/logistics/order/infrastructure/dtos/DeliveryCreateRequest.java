package com.two_ddang.logistics.order.infrastructure.dtos;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class DeliveryCreateRequest {

    private UUID orderId;
    private UUID departHubId;
    private UUID arrivedHubId;
    private String deliveryAddress;
    private UUID receiveCompanyId;

    public DeliveryCreateRequest(UUID deliveryId) {

    }

    public static DeliveryCreateRequest of(UUID orderId, UUID reqCompanyId, CompanyDetailResponse resCompanyResponse) {
        return DeliveryCreateRequest.builder()
                .orderId(orderId)
                .departHubId(reqCompanyId)
                .arrivedHubId(resCompanyResponse.getHubId())
                .deliveryAddress(resCompanyResponse.getAddress())
                .receiveCompanyId(resCompanyResponse.getCompanyId())
                .build();
    }

}
