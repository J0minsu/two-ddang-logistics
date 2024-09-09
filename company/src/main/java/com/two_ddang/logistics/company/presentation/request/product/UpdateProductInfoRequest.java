package com.two_ddang.logistics.company.presentation.request.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateProductInfoRequest {

    private String productName;
    private String description;
    private Long price;
    private Integer stock;
    private Boolean isSoldOut;
}
