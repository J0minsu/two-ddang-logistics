package com.two_ddang.logistics.order.infrastructure;

import com.two_ddang.logistics.core.util.ResponseDTO;
import com.two_ddang.logistics.order.infrastructure.dtos.HubProductOutboundRequest;
import com.two_ddang.logistics.order.infrastructure.dtos.HubProductRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@FeignClient(name = "hub")
public interface HubFeignClient extends HubService{

    @Override
    @PostMapping("/api/v1/hubs/{hubId}/products/outbound")
    ResponseEntity<ResponseDTO<HubProductRes>> order(@PathVariable UUID hubId, HubProductOutboundRequest request);
}
