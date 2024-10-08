package com.two_ddang.logistics.hub.application.service.feign.transit.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(title = "허브 간 경로 등록/수정 요청 DTO")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HubRouteModifyRequest {

    private UUID departmentHubId;
    private UUID arriveHubId;
    private int takeTime;
    private String route;
    private UUID updateDeliveryAgentId;

}