package com.two_ddang.logistics.ai.service.dto;

import com.two_ddang.logistics.ai.entity.AiType;
import com.two_ddang.logistics.ai.entity.Gemini;

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
