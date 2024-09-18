package com.two_ddang.logistics.ai.application.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class WeatherService {

    private final String serviceKey;
    private final ObjectMapper objectMapper;

    private String baseUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0";

    public WeatherService(@Value("${weather.api-key}") String serviceKey, ObjectMapper objectMapper) {
        this.serviceKey = serviceKey;
        this.objectMapper = objectMapper;
    }

    public String getWeatherInfo(Double latitude, Double longitude) {
    
        // 오늘 날짜와 시간 가져오기
        String baseDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        // 현재 시간을 "HH00" 형식으로 변환
        String baseTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH")) + "00";

        // WebClient로 기상청 API 호출
        WebClient client = WebClient.create(baseUrl);

        String responseBody = client.get()
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
                        // 바디 부분 추출 (API 응답의 JSON 구조에 따라 수정 필요)
                        JsonNode bodyNode = jsonNode.path("response").path("body");
                        return Mono.just(bodyNode.toString()); // 바디를 String으로 변환
                    } catch (Exception e) {
                        return Mono.error(e); // 파싱 에러 처리
                    }
                })
                .block(); // 동기적으로 대기

        return responseBody; // 바디만 추출된 응답 반환

    }

}
