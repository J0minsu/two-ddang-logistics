package com.two_ddang.logistics.ai.service;

import com.two_ddang.logistics.ai.entity.Gemini;
import com.two_ddang.logistics.ai.exception.AINotFoundException;
import com.two_ddang.logistics.ai.repository.GeminiRepository;
import com.two_ddang.logistics.ai.service.dto.GeminiReadResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class GeminiService {

    private final GeminiRepository geminiRepository;


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
