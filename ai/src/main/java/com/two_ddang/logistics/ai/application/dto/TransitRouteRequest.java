package com.two_ddang.logistics.ai.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "허브 배송 경로 요청 DTO", description = "허브 경로 최적화 시 필요한 요청 데이터")
public class TransitRouteRequest {

    @Schema(title = "출발 허브 ID", example = "g6h7i8j9-k0l1-m2n3-o4p5-q7r8s9t0v1w2")
    private UUID departmentHubId;
    @Schema(title = "출발 허브 주소", example = "울산 남구 중앙로 201")
    private String departureAddress;
    @Schema(title = "도착 허브 ID", example = "h7i8j9k0-l1m2-n3o4-p5q6-r8s9t0v1w2x3")
    private UUID arriveHubId;
    @Schema(title = "도착 허브 주소", example = "세종특별자치시 한누리대로 2130")
    private String arriveAddress;

}
