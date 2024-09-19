package com.two_ddang.logistics.delivery.application.dto;

import com.two_ddang.logistics.core.entity.DriverAgentType;
import com.two_ddang.logistics.delivery.domain.model.DeliveryAgent;
import com.two_ddang.logistics.delivery.domain.vo.DeliveryAgentVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Schema(title = "운/배송 기사 응답 DTO")
@AllArgsConstructor
public class DeliveryAgentRes {

    private int userId;
    private UUID slackId;
    private UUID deliveryAgentId;
    private DriverAgentType type;
    private UUID hubId;

    public static DeliveryAgentRes fromVO(DeliveryAgentVO agent) {
        return new DeliveryAgentRes(agent.getUserId(), agent.getSlackId(), agent.getId(), agent.getType(), agent.getRegisteredHubId());
    }
}
