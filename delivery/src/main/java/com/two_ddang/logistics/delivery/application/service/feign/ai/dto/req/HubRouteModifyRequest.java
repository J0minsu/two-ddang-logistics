package com.two_ddang.logistics.delivery.application.service.feign.ai.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(title = "허브 간 경로 등록/수정 요청 DTO")
@AllArgsConstructor
public class HubRouteModifyRequest {

    private UUID departmentHubId;
    private UUID arriveHubId;
    private int takeTime;
    private String route;
    private UUID updateDeliveryAgentId;

}
