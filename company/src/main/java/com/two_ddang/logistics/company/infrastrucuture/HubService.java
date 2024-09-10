package com.two_ddang.logistics.company.infrastrucuture;

import com.two_ddang.logistics.company.infrastrucuture.dtos.HubInfo;
import com.two_ddang.logistics.company.infrastrucuture.dtos.RestockHubRequest;
import com.two_ddang.logistics.core.util.ResponseDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface HubService {

    ResponseDTO<HubInfo> getHubInfo(UUID hubId);

    ResponseDTO<Void> restock(RestockHubRequest request);
}
