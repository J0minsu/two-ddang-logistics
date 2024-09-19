package com.two_ddang.logistics.ai.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "위경도 응답 DTO", description = "주소값을 통해 위경도를 추출한 응답 데이터")
public record LatLngRequestDto(
        @Schema(title = "위도", example = "32.721215")
        Double latitude,
        @Schema(title = "경도", example = "126.721215")
        Double longitude
) {

}
