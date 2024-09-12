package com.two_ddang.logistics.order.infrastructure;

import com.two_ddang.logistics.core.util.ResponseDTO;
import com.two_ddang.logistics.order.infrastructure.dtos.HubProductOutboundRequest;
import com.two_ddang.logistics.order.infrastructure.dtos.HubProductRes;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface HubService {

    ResponseEntity<ResponseDTO<HubProductRes>> order(UUID hubId, HubProductOutboundRequest request);
}
