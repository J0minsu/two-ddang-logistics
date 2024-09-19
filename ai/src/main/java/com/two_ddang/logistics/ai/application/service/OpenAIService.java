package com.two_ddang.logistics.ai.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenAIService {

    private final OpenAiChatModel openAiChatModel;

    public String chatToGPT(String query) {
        return openAiChatModel.call(query);
    }

}
