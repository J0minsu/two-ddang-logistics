package com.two_ddang.logistics.ai.presentation.controller;

import com.slack.api.methods.SlackApiException;
import com.two_ddang.logistics.ai.application.dto.RecommendTransitRouteRequest;
import com.two_ddang.logistics.ai.application.dto.RecommendTransitRouteResponse;
import com.two_ddang.logistics.ai.application.service.GeminiService;
import com.two_ddang.logistics.core.util.CurrentPassport;
import com.two_ddang.logistics.core.util.Passport;
import com.two_ddang.logistics.core.util.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@SecurityRequirement(name = "Bearer Authentication")
@SecurityScheme( name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "Bearer")
@RestController
@RequestMapping("/api/v1/ais")
@RequiredArgsConstructor
@Tag(name = "AI API")
public class GeminiController {

    private final GeminiService geminiService;

    @GetMapping
    @Operation(summary = "날씨 정보 요약", description = "날씨 정보 예약 API")
    public ResponseEntity<ResponseDTO<String>> chatToGemini(
            @Parameter(hidden = true) @CurrentPassport Passport passport
    ) throws IOException, ExecutionException, InterruptedException {

        return ResponseEntity.ok(ResponseDTO.okWithData(geminiService.chatToGeminiAndSaveTest()));
    }

    @PostMapping("/routes")
    @Operation(summary = "허브 배송 경로 최적화", description = "허브 배송 경로 최적화 API")
    public ResponseEntity<ResponseDTO<RecommendTransitRouteResponse>> recommendRoute(
            @RequestBody Map<UUID, RecommendTransitRouteRequest> request,
            @Parameter(hidden = true) @CurrentPassport Passport passport) throws IOException, SlackApiException {

        return ResponseEntity.ok(ResponseDTO.okWithData(geminiService.recommendRoute(request)));
    }
}
