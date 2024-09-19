package com.two_ddang.logistics.company.infrastrucuture;

import com.two_ddang.logistics.company.infrastrucuture.dtos.HubRes;
import com.two_ddang.logistics.company.infrastrucuture.dtos.RestockHubRequest;
import com.two_ddang.logistics.core.util.ResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Service
public interface HubService {

    ResponseDTO<HubRes> getHubInfo(UUID hubId);

    void inboundProduct(UUID hubId, RestockHubRequest request);

    ResponseDTO<HubRes> findHubByMangerUserId(@PathVariable Integer userId);
}
