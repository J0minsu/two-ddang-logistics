package com.two_ddang.logistics.company.presentation.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateProductRequest {

    private String productName;
    private String description = "";
    private Long price = 0L;
    private Integer quantity = 0;
    private UUID companyId;
    private UUID hubId;
}
