package com.two_ddang.logistics.order.infrastructure.dtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class HubProductOutboundRequest {

    private UUID productId;
    private UUID companyId;
    private int quantity;


    public static HubProductOutboundRequest of(UUID productId, UUID companyId, int quantity) {
        return HubProductOutboundRequest.builder()
                .productId(productId)
                .companyId(companyId)
                .quantity(quantity)
                .build();
    }
}
