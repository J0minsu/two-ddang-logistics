package com.two_ddang.logistics.hub.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@Schema(title = "허브 물품 응답 DTO")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HubProductRes {

    private UUID hubId;
    private UUID productId;
    private UUID companyId;
    private String productName;
    private int quantity;

    public static HubProductRes fromVO(
            UUID hubId, UUID productId, UUID companyId,
            String productName, int quantity
    ) {
        return new HubProductRes(hubId, productId, companyId, productName, quantity);
    }

    public static HubProductRes example() {

        UUID uuid = UUID.randomUUID();

        return new HubProductRes(uuid, uuid, uuid, "천사 지파이", 3600);
    }


}
