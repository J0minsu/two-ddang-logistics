package com.two_ddang.logistics.ai.presentation.controller;

import com.slack.api.methods.SlackApiException;
import com.two_ddang.logistics.ai.application.dto.RecommendTransitRouteRequest;
import com.two_ddang.logistics.ai.application.dto.RecommendTransitRouteResponse;
import com.two_ddang.logistics.ai.application.service.GeminiService;
import com.two_ddang.logistics.core.util.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/ais")
@RequiredArgsConstructor
public class GeminiController {

    private final GeminiService geminiService;

    @GetMapping
    public ResponseEntity<ResponseDTO<String>> chatToGemini(
    ) throws IOException, ExecutionException, InterruptedException {

        return ResponseEntity.ok(ResponseDTO.okWithData(geminiService.chatToGeminiAndSaveTest()));
    }

    @PostMapping("/routes")
    public ResponseEntity<ResponseDTO<RecommendTransitRouteResponse>> recommendRoute(
            @RequestBody Map<UUID, RecommendTransitRouteRequest> request) throws IOException, SlackApiException {

        return ResponseEntity.ok(ResponseDTO.okWithData(geminiService.recommendRoute(request)));
    }
}
