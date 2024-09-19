package com.two_ddang.logistics.ai.application.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;

@Service
public class WeatherService {

    private final String serviceKey;
    private final ObjectMapper objectMapper;

    private String baseUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0";

    public WeatherService(@Value("${weather.api-key}") String serviceKey, ObjectMapper objectMapper) {
        this.serviceKey = serviceKey;
        this.objectMapper = objectMapper;
    }

    @Async
    public CompletableFuture<String> getWeatherInfo(Double latitude, Double longitude) {
        // 오늘 날짜와 시간 가져오기
        String baseDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String baseTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH")) + "00";

        // WebClient로 기상청 API 호출 비동기 처리
        WebClient client = WebClient.create(baseUrl);

        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("serviceKey", serviceKey)
                        .queryParam("dataType", "JSON")
                        .queryParam("base_date", baseDate)
                        .queryParam("base_time", baseTime)
                        .queryParam("nx", longitude)
                        .queryParam("ny", latitude)
                        .build())
                .retrieve()
                .bodyToMono(String.class) // 응답을 String으로 받기
                .flatMap(response -> {
                    try {
                        // JSON 응답 파싱
                        JsonNode jsonNode = objectMapper.readTree(response);
                        JsonNode bodyNode = jsonNode.path("response").path("body");
                        return Mono.just(bodyNode.toString()); // 바디를 String으로 변환
                    } catch (Exception e) {
                        return Mono.error(e); // 파싱 에러 처리
                    }
                })
                .toFuture(); // Mono를 CompletableFuture로 변환
    }
}
