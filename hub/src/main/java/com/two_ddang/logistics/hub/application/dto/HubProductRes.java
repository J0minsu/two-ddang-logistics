package com.two_ddang.logistics.hub.application.dto;

import com.two_ddang.logistics.hub.domain.vo.HubProductVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

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

    public static HubProductRes fromVO(HubProductVO hubProduct) {
        return new HubProductRes(hubProduct.getHubId(), hubProduct.getProductId(), hubProduct.getCompanyId(),
                hubProduct.getProductName(), hubProduct.getStock());
    }

    public static HubProductRes example() {

        UUID uuid = UUID.randomUUID();

        return new HubProductRes(uuid, uuid, uuid, "천사 지파이", 3600);
    }


}
