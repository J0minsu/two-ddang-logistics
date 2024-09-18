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
import com.two_ddang.logistics.ai.domain.repository.GeminiRepository;
import com.two_ddang.logistics.ai.infrastructure.exception.AINotFoundException;
import com.two_ddang.logistics.core.entity.AiType;
import com.two_ddang.logistics.core.entity.DeliveryStatus;
import com.two_ddang.logistics.core.entity.DriverAgentType;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class GeminiService {

    private final GeminiRepository geminiRepository;
    private final VertexAiGeminiChatModel vertexAiGeminiChatModel;
    private final DeliveryService deliveryService;

    private final String geoCodingApiKey;

    public GeminiService(GeminiRepository geminiRepository,
                         VertexAiGeminiChatModel vertexAiGeminiChatModel,
                         DeliveryService deliveryService,
                         @Value("${google.maps.api.key}")String geoCodingApiKey) {
        this.geminiRepository = geminiRepository;
        this.vertexAiGeminiChatModel = vertexAiGeminiChatModel;
        this.deliveryService = deliveryService;
        this.geoCodingApiKey = geoCodingApiKey;
    }


    public String chatToGeminiAndSave(String prompt, Long userId, AiType aiType) {

        //기사별 배송 정보 가져오기

        //기사의 소속 허브의 그 날의 날씨 정보 불러오기

        //두 내용으로 content 만들기


        String content = vertexAiGeminiChatModel.call(prompt);

        Gemini gemini = new Gemini(userId, prompt, aiType, content);
        geminiRepository.save(gemini);

        return content;
    }


    public Page<GeminiReadResponseDto> getAllGemini(Pageable pageable) {
        return geminiRepository.findAllByDeletedIsFalse(pageable)
                .map(GeminiReadResponseDto::fromEntity);
    }

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

    @Scheduled(cron = "0 0 6 * * *") //매일 오전 6시
    @Async
    protected void transitAgentDeliveryInfo() {
        List<DeliveryRes> responses = deliveryService.getTransitAddressAndSlackId(DriverAgentType.TRANSIT,
                DeliveryStatus.BEFORE_TRANSIT);

        List<LatLngRequestDto> requestToGemini = new ArrayList<>();

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







    }
}
