package com.two_ddang.logistics.ai.application.service.feign.delivery;

import com.two_ddang.logistics.ai.application.service.feign.delivery.dto.DeliveryRes;
import com.two_ddang.logistics.ai.application.service.feign.delivery.dto.TransitRes;
import com.two_ddang.logistics.ai.application.service.feign.delivery.fallback.DeliveryFallbackFactory;
import com.two_ddang.logistics.ai.infrastructure.config.FeignClientConfig;
import com.two_ddang.logistics.core.entity.DeliveryStatus;
import com.two_ddang.logistics.core.entity.DriverAgentType;
import com.two_ddang.logistics.core.util.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "delivery",
        configuration = FeignClientConfig.class,
        fallbackFactory = DeliveryFallbackFactory.class
)
public interface DeliveryFeignClient extends DeliveryService {

//    @GetMapping("/api/v1/deliveries") //group by로 슬랙 아이디 별로 묶으면 됨! slackId 없는 것도 보내도록 요청
//    List<DeliveryRes> getTransitAddressAndSlackId(@RequestParam("agent-type") DriverAgentType agentType,
//                                            @RequestParam("status")DeliveryStatus status);


    @PostMapping("api/v1/transits/schedules")
    ResponseDTO<List<TransitRes>> hubTransitSchedule();
}
