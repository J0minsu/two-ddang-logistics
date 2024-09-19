package com.two_ddang.logistics.ai.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "허브 간 배송 경로 최적화 요청 DTO", description = "허브 배송 담장자에게 최적화된 허브 경로 제공을 위한 요청 데이터")
public class RecommendTransitRouteRequest {

    @Schema(title = "배송경로 리스트", example = """
            "routes": [
                {
                  "departmentHubId": "c9d4f2f4-cc37-4cde-bfb3-5a1b3c6e5f34",
                  "departureAddress": "서울특별시 송파구 송파대로 55",
                  "arriveHubId": "a1e2f3g4-h5i6-j7k8-l9m0-n1o2p3q4r5s6",
                  "arriveAddress": "경기도 고양시 덕양구 권율대로 570"
                }]""")
    private List<TransitRouteRequest> routes;

}
