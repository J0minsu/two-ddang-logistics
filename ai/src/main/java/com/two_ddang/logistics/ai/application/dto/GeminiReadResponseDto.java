package com.two_ddang.logistics.ai.application.dto;

import com.two_ddang.logistics.core.entity.AiType;
import com.two_ddang.logistics.ai.domain.model.Gemini;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(title = "AI 조회 응답 DTO", description = "AI 기록 요청 시 응답해주는 DTO")
public record GeminiReadResponseDto(

        @Schema(title = "AI 테이블 ID", example = "b0b1a1b6-2a4d-44b1-a1b6-2a4d44b1a1b6")
        UUID id,
        @Schema(title = "질의", example = "질문 있습니다.")
        String prompt,
        @Schema(title = "응답", example = "응답입니다.")
        String content,
        @Schema(title = "AI 요청 서버", example = "DELIVERY.")
        AiType aiType,
        @Schema(title = "사용자 ID", example = "1")
        Long userId,
        @Schema(title = "타입 연관 ID", example = "c2b1a1b6-2a4d-44b1-a1b6-22a12c3b")
        UUID referenceId
) {
    public static GeminiReadResponseDto fromEntity(Gemini gemini) {
        return new GeminiReadResponseDto(
                gemini.getId(), gemini.getPrompt(), gemini.getContent(),
                gemini.getAiType(), gemini.getUserId(), gemini.getReferenceId()
        );
    }

}
