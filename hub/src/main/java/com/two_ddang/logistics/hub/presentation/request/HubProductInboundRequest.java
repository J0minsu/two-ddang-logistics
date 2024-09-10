package com.two_ddang.logistics.hub.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@Schema(title = "허브 물품 입고 요청 DTO")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HubProductInboundRequest {

    private UUID hubId;
    private UUID productId;
    private UUID companyId;
    private String productName;
    private int quantity;

}
