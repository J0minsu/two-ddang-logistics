package com.two_ddang.logistics.order.infrastructure.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CompanyProductResponse {

    private UUID productId;
    private String productName;
    private Long price;
    private Integer stock;
    private Boolean isSoldOut;
}
