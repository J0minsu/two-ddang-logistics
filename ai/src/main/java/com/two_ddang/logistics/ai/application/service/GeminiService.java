package com.two_ddang.logistics.ai.application.service;

import com.two_ddang.logistics.ai.domain.AiType;
import com.two_ddang.logistics.ai.domain.model.Gemini;
import com.two_ddang.logistics.ai.infrastructure.exception.AINotFoundException;
import com.two_ddang.logistics.ai.domain.repository.GeminiRepository;
import com.two_ddang.logistics.ai.application.dto.GeminiReadResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class GeminiService {

    private final GeminiRepository geminiRepository;
    private final VertexAiGeminiChatModel vertexAiGeminiChatModel;

    public String chatToGeminiAndSave(String prompt, Long userId, AiType aiType) {
        String content = vertexAiGeminiChatModel.call(prompt);

        Gemini gemini = new Gemini(userId, prompt, aiType, content);
        geminiRepository.save(gemini);

        return content;
    }


    public Page<GeminiReadResponseDto> getAllGemini(Pageable pageable) {
        return geminiRepository.findAllByDeletedIsFalse(pageable)
                .map(GeminiReadResponseDto::fromEntity);
    }

    public GeminiReadResponseDto getGeminiById(UUID aiId) {
        Gemini gemini = geminiRepository.findByIdAndDeletedIsFalse(aiId)
                .orElseThrow(AINotFoundException::new);

        return GeminiReadResponseDto.fromEntity(gemini);
    }

    public void deleteById(Integer userId ,UUID aiId) {
        Gemini gemini = geminiRepository.findByIdAndDeletedIsFalse(aiId)
                .orElseThrow(AINotFoundException::new);

        gemini.delete(userId);
    }
}
