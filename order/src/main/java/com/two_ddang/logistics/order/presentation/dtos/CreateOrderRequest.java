package com.two_ddang.logistics.order.presentation.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Create order request")
public class CreateOrderRequest {

    @Schema(description = "Requesting company ID")
    private UUID reqCompanyId;

    @Schema(description = "Receiving company ID")
    private UUID resCompanyId;

    @Schema(description = "List of products in the order", implementation = CreateOrderProductRequest.class)
    private List<CreateOrderProductRequest> orderProducts;

}

