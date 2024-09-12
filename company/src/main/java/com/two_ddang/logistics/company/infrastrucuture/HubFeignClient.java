package com.two_ddang.logistics.company.infrastrucuture;

import com.two_ddang.logistics.company.infrastrucuture.dtos.HubInfo;
import com.two_ddang.logistics.company.infrastrucuture.dtos.RestockHubRequest;
import com.two_ddang.logistics.core.util.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@FeignClient(name = "hub")
public interface HubFeignClient extends HubService {


    @Override
    @PostMapping("/api/v1/hubs/products")
    ResponseDTO<Void> restock(RestockHubRequest request);

    @GetMapping("/api/v1/hubs/{hubId}")
    ResponseDTO<HubInfo> getHubInfo(@PathVariable UUID hubId);
}
