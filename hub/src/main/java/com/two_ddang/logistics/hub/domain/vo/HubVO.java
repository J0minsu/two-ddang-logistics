package com.two_ddang.logistics.hub.domain.vo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class HubVO {

    private final UUID id;
    private final String name;
    private final String address;
    private final String latitude;
    private final String longitude;
    private final List<HubAgentVO> hubAgents;
    private final List<HubProductVO> hubProducts;
    private final List<HubRouteVO> arriveRoutes;
    private final List<HubRouteVO> departmentRoutes;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final LocalDateTime deletedAt;
    private final Integer createdBy;
    private final Integer updatedBy;
    private final Integer deletedBy;

    private final boolean isDeleted;


}
