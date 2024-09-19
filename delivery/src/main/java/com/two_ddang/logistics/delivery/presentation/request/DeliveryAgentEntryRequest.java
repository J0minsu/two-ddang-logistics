package com.two_ddang.logistics.delivery.presentation.request;

import com.two_ddang.logistics.core.entity.DriverAgentType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Schema(title = "운/배송 기사 등록 요청 DTO")
@AllArgsConstructor
public class DeliveryAgentEntryRequest {

    private int userId;
    private UUID slackId;
    private UUID deliveryAgentId;
    private DriverAgentType type;
    private UUID hubId;

}
