package com.two_ddang.logistics.order.infrastructure;

import com.two_ddang.logistics.core.util.ResponseDTO;
import com.two_ddang.logistics.order.infrastructure.dtos.HubProductOutboundRequest;
import com.two_ddang.logistics.order.infrastructure.dtos.HubRes;

import java.util.UUID;

public interface HubService {

    void outbound(UUID hubId, HubProductOutboundRequest request);

    ResponseDTO<HubRes> findHubByMangerUserId(Integer userId);
}
