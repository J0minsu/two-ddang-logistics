package com.two_ddang.logistics.ai.presentation.controller;

import com.two_ddang.logistics.ai.application.service.GeminiService;
import com.two_ddang.logistics.core.util.CurrentPassport;
import com.two_ddang.logistics.core.util.Passport;
import com.two_ddang.logistics.core.util.ResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/ais")
@RequiredArgsConstructor
@Tag(name = "Gemini API")
public class GeminiController {

    private final GeminiService geminiService;

    @GetMapping
    public ResponseEntity<ResponseDTO<String>> chatToGemini(
            @CurrentPassport Passport passport
            ) throws IOException, ExecutionException, InterruptedException {
        return ResponseEntity.ok(ResponseDTO.okWithData(geminiService.chatToGeminiAndSaveTest()));
    }

    @GetMapping("/recommend-routes")
    public String recommendRoute(@RequestParam("department") String departmentAddress,
                                 @RequestParam("arrive") String arriveAddress) {

        return geminiService.recommendRoute(departmentAddress,arriveAddress);
    }
}
