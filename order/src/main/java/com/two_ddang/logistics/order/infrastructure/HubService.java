package com.two_ddang.logistics.order.infrastructure;

import com.two_ddang.logistics.order.infrastructure.dtos.HubProductOutboundRequest;

import java.util.UUID;

public interface HubService {

    void outbound(UUID hubId, HubProductOutboundRequest request);

    UUID getHubIdByUserId(Integer userId);
}
