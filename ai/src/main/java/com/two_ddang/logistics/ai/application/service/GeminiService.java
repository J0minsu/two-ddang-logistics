package com.two_ddang.logistics.ai.application.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.two_ddang.logistics.ai.application.dto.GeminiReadResponseDto;
import com.two_ddang.logistics.ai.application.dto.LatLngRequestDto;
import com.two_ddang.logistics.ai.application.service.feign.delivery.DeliveryService;
import com.two_ddang.logistics.ai.application.service.feign.delivery.dto.DeliveryRes;
import com.two_ddang.logistics.ai.domain.model.Gemini;
import com.two_ddang.logistics.ai.domain.model.SlackEntity;
import com.two_ddang.logistics.ai.domain.repository.GeminiRepository;
import com.two_ddang.logistics.ai.infrastructure.exception.AINotFoundException;
import com.two_ddang.logistics.core.entity.AiType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
@Transactional
@Slf4j
public class GeminiService {

    private final GeminiRepository geminiRepository;

    private final VertexAiGeminiChatModel vertexAiGeminiChatModel;

    private final DeliveryService deliveryService;

    private final String geoCodingApiKey;

    private final WeatherService weatherService;

    private final SlackService slackService;

    public GeminiService(GeminiRepository geminiRepository,
                         VertexAiGeminiChatModel vertexAiGeminiChatModel,
                         DeliveryService deliveryService,
                         @Value("${google.maps.api.key}") String geoCodingApiKey,
                         WeatherService weatherService,
                         SlackService slackService) {
        this.geminiRepository = geminiRepository;
        this.vertexAiGeminiChatModel = vertexAiGeminiChatModel;
        this.deliveryService = deliveryService;
        this.geoCodingApiKey = geoCodingApiKey;
        this.weatherService = weatherService;
        this.slackService = slackService;
    }


    public String chatToGeminiAndSaveTest() throws ExecutionException, InterruptedException, IOException {

        String weatherInfo = weatherService.getWeatherInfo(60, 127);
        log.info(weatherInfo);

        String prompt = weatherInfo + "이 정보를 요약해서 알려줘.";
        String context = vertexAiGeminiChatModel.call(prompt);

        Gemini gemini = new Gemini(prompt, AiType.DELIVERY, context);
        geminiRepository.save(gemini);

        // context를 슬랙 메시지 payload로 변환
        String payload = "{\"text\":\"" + context + "\"}";

        // 슬랙 메시지 전송
        String message = slackService.sendMessage(payload);
        log.info(message);

        SlackEntity slackEntity = new SlackEntity(message, LocalDateTime.now());
        slackService.saveMessage(slackEntity);

        return message;
    }

    public String recommendRoute(String departAddress, String arriveAddress) {
        String context = "";
        return vertexAiGeminiChatModel.call(context);
    }


    public Page<GeminiReadResponseDto> getAllGemini(Pageable pageable) {
        return geminiRepository.findAllByDeletedIsFalse(pageable)
                .map(GeminiReadResponseDto::fromEntity);
    }

    public GeminiReadResponseDto getGeminiById(UUID aiId) {
        Gemini gemini = geminiRepository.findByIdAndDeletedAtIsNull(aiId)
                .orElseThrow(AINotFoundException::new);

        return GeminiReadResponseDto.fromEntity(gemini);
    }

    public void deleteById(Integer userId, UUID aiId) {
        Gemini gemini = geminiRepository.findByIdAndDeletedAtIsNull(aiId)
                .orElseThrow(AINotFoundException::new);

        gemini.delete(userId);
    }


    @Scheduled(cron = "0 0 6 * * *") //매일 오전 6시
    @Async
    protected void transitAgentDeliveryInfo() throws IOException, ExecutionException, InterruptedException {
//        List<DeliveryRes> responses = deliveryService.getTransitAddressAndSlackId(DriverAgentType.TRANSIT,
//                DeliveryStatus.BEFORE_TRANSIT);

//        Set<LatLngRequestDto> request = ToLanLng(responses);
//        List<String> weatherInfos = new ArrayList<>();
        String weatherInfo = weatherService.getWeatherInfo(60, 127);

        String prompt = weatherInfo + "이 정보를 요약해서 500자 이내로 알려줘.";
        String context = vertexAiGeminiChatModel.call(prompt);

        Gemini gemini = new Gemini(prompt, AiType.DELIVERY, context);
        geminiRepository.save(gemini);

        slackService.sendMessage(context);

        SlackEntity slackEntity = new SlackEntity(context, LocalDateTime.now());
        slackService.saveMessage(slackEntity);


//        request.forEach(requestDto -> {
//            weatherInfos.add(weatherService.getWeatherInfo(requestDto.latitude(), requestDto.longitude()));
//        });
    }

    @Scheduled(cron = "0 0 8 * * *") //매일 오전 8시
    @Async
    protected void hubDeliveryInfo() {

        String context = "";
        vertexAiGeminiChatModel.call(context);


    }

    private Set<LatLngRequestDto> ToLanLng(List<DeliveryRes> responses) {
        Set<LatLngRequestDto> requestToGemini = new HashSet<>();

        responses.forEach(response -> {
            String address = response.address();

            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey(geoCodingApiKey)
                    .build();

            try {
                //요청을 동기적으로 실행 -> 비동기 식으로 바꿔줘야 함.
                GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
                if (results.length > 0) {
                    LatLng location = results[0].geometry.location;
                    requestToGemini.add(new LatLngRequestDto(location.lat, location.lng));
                }
            } catch (IOException | InterruptedException | ApiException e) {
                log.info(String.valueOf(e), "Geocoding API 호출 중 오류 발생!");
                throw new RuntimeException(e);
            }
        });

        return requestToGemini;
    }

}
