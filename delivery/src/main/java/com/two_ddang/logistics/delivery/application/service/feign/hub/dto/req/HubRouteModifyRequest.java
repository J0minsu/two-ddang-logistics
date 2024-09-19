package com.two_ddang.logistics.delivery.application.service.feign.hub.dto.req;

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

/**
 * 내가 department 에서 출발하는데 경유해야할 곳이 List<ArriveHub> 야. 최단 거리의 운송 순서 sequence 및 경로, 예상 소요시간을 json 형식으로 반환해줘
 */