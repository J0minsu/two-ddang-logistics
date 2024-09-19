package com.two_ddang.logistics.ai.application.service.feign.delivery.dto;

import com.two_ddang.logistics.core.entity.TransitStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TransitRes {

    private UUID transitId;

    private UUID transitAgentId;

    private UUID departmentHubId;

    private TransitStatus transitStatus;

    private int totalCount;

    private int stayCount;

    private Map<Integer, List<TransitRouteRes>> route;

}
