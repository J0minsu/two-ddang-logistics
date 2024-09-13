package com.two_ddang.logistics.order.infrastructure.dtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HubProductRes {

    private UUID hubId;
    private UUID productId;
    private UUID companyId;
    private String productName;
    private int quantity;


}
