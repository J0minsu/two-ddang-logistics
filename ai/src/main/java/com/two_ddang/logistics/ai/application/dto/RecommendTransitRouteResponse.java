package com.two_ddang.logistics.ai.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "허브 간 배송 경로 최적화 응답 DTO", description = "최적화된 배송 경로를 응답 값으로 보내주는 데이터")
public class RecommendTransitRouteResponse {

    @Schema(title = "경로 정보", example = "1:    {\n" +
            "      \"sequence\": 1,\n" +
            "      \"departmentHubId\": \"서울특별시 송파구 송파대로 55\",\n" +
            "      \"departureAddress\": \"서울특별시 송파구 송파대로 55\",\n" +
            "      \"arriveHubId\": \"경기도 고양시 덕양구 권율대로 570\",\n" +
            "      \"arriveAddress\": \"경기도 고양시 덕양구 권율대로 570\",\n" +
            "      \"estimateTime\": \"1시간 10분\",\n" +
            "      \"estimateDistance\": \"65km\",\n" +
            "      \"route\": [\n" +
            "        \"서울특별시 송파구 송파대로 55\",\n" +
            "        \"경기도 고양시 덕양구 권율대로 570\"\n" +
            "      ]\n" +
            "    ")
    //sequence, route-info
    Map<Integer, TransitRouteResponse> routes;

}
