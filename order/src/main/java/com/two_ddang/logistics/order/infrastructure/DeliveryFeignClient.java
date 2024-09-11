package com.two_ddang.logistics.order.infrastructure;

import com.two_ddang.logistics.core.util.ResponseDTO;
import com.two_ddang.logistics.order.infrastructure.dtos.DeliveryCreateRequest;
import com.two_ddang.logistics.order.infrastructure.dtos.DeliveryRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "delivery")
public interface DeliveryFeignClient extends DeliveryService{

    @Override
    @PostMapping("/api/v1/deliveries")
    ResponseDTO<DeliveryRes> create(@RequestBody DeliveryCreateRequest request);
}
