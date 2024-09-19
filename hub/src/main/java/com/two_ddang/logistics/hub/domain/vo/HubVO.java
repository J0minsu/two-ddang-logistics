package com.two_ddang.logistics.hub.domain.vo;

import com.two_ddang.logistics.hub.domain.model.Hub;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class HubVO implements Serializable {

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

    public static HubVO fromEntity(Hub hub) {

        hub = Objects.isNull(hub) ? Hub.emptyObject() : hub;

        List<HubAgentVO> hubAgentsForVO = hub.getHubAgents()
                .stream().map(HubAgentVO::fromEntity).toList();

        List<HubProductVO> hubProducesForVO = hub.getHubProducts()
                .stream().map(HubProductVO::fromEntity).toList();

        List<HubRouteVO> arriveRoutesForVO = hub.getArriveRoutes()
                .stream().map(HubRouteVO::fromEntity).toList();

        List<HubRouteVO> departmentRoutesForVO = hub.getDepartmentRoutes()
                .stream().map(HubRouteVO::fromEntity).toList();

        return new HubVO(hub.getId(), hub.getName(), hub.getAddress(), hub.getLatitude(), hub.getLongitude(),
                hubAgentsForVO, hubProducesForVO, arriveRoutesForVO, departmentRoutesForVO,
                hub.getCreatedAt(), hub.getUpdatedAt(), hub.getDeletedAt(), hub.getCreatedBy(), hub.getUpdatedBy(), hub.getDeletedBy(), hub.isDeleted());

    }



}
