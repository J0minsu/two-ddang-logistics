package com.two_ddang.logistics.ai.application.dto;

import com.two_ddang.logistics.ai.domain.AiType;
import com.two_ddang.logistics.ai.domain.model.Gemini;

import java.util.UUID;

public record GeminiReadResponseDto(
        UUID id,
        String prompt,
        String content,
        AiType aiType,
        Long userId,
        UUID referenceId
) {
    public static GeminiReadResponseDto fromEntity(Gemini gemini) {
        return new GeminiReadResponseDto(
                gemini.getId(), gemini.getPrompt(), gemini.getContent(),
                gemini.getAiType(), gemini.getUserId(), gemini.getReferenceId()
        );
    }

}
