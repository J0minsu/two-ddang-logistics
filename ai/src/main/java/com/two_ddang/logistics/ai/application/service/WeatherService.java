package com.two_ddang.logistics.ai.application.service;

import com.fasterxml.jackson.core.JsonParseException;
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

    private String baseUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";

    public WeatherService(@Value("${weather.api-key}") String serviceKey, ObjectMapper objectMapper) {
        this.serviceKey = serviceKey;
        this.objectMapper = objectMapper;
    }

    public String getWeatherInfo(Integer latitude, Integer longitude) {
        // 오늘 날짜와 시간 가져오기
        String baseDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String baseTime = LocalTime.now().minusHours(1).format(DateTimeFormatter.ofPattern("HH")) + "00";

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
                .bodyToMono(String.class)
                .doOnNext(response -> System.out.println("Response: " + response)) // 응답 출력
                .flatMap(response -> {
                    try {
                        JsonNode jsonNode = objectMapper.readTree(response);
                        JsonNode bodyNode = jsonNode.path("response").path("body");
                        return Mono.just(bodyNode.toString());
                    } catch (JsonParseException e) {
                        return Mono.error(new RuntimeException("응답이 JSON 형식이 아닙니다: " + response, e));
                    } catch (Exception e) {
                        return Mono.error(e);
                    }
                })
                .block();
    }
}
