package com.two_ddang.logistics.ai.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "허브 간 이동 경로 최적화 응답 DTO", description = "허브 간 경로 최적화 요청시 응답값으로 반환되는 데이터")
public class TransitRouteResponse {

    @Schema(title = "순서", example = "1")
    private int sequence;
    @Schema(title = "출발 허브 ID", example = "g6h7i8j9-k0l1-m2n3-o4p5-q7r8s9t0v1w2")
    private UUID departmentHubId;
    @Schema(title = "출발 허브 주소", example = "울산 남구 중앙로 201")
    private String departureAddress;
    @Schema(title = "도착 허브 ID", example = "h7i8j9k0-l1m2-n3o4-p5q6-r8s9t0v1w2x3")
    private UUID arriveHubId;
    @Schema(title = "도착 허브 주소", example = "세종특별자치시 한누리대로 2130")
    private String arriveAddress;
    @Schema(title = "예상 소요 시간", example = "120분")
    private int estimateTime;
    @Schema(title = "예상 거리", example = "200km")
    private int estimateDistance;
    @Schema(title = "경로", example = "울산 남구 중앙로 201 -> 세종특별자치시 한누리대로 2130 ")
    private String route;
}
