package com.two_ddang.logistics.ai.presentation.controller;

import com.two_ddang.logistics.ai.application.service.GeminiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ais")
@RequiredArgsConstructor
public class GeminiController {

    private final GeminiService geminiService;

    @GetMapping("/recommend-routes")
    public String recommendRoute(@RequestParam("department") String departmentAddress,
                                 @RequestParam("arrive") String arriveAddress) {

//        return geminiService.recommendRoute(departmentAddress,arriveAddress);
        return null;
    }
}
