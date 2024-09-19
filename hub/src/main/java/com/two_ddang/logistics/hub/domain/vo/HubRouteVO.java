package com.two_ddang.logistics.hub.domain.vo;

import com.two_ddang.logistics.hub.domain.model.Hub;
import com.two_ddang.logistics.hub.domain.model.HubRoute;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class HubRouteVO implements Serializable {

    private final UUID id;
    private final String departmentAddress;
    private final UUID departmentHubId;
    private final String arriveAddress;
    private final UUID arriveHubId;
    private final int takeTime;
    private final String route;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final LocalDateTime deletedAt;
    private final Integer createdBy;
    private final Integer updatedBy;
    private final Integer deletedBy;

    private final boolean isDeleted;

    public static HubRouteVO fromEntity(HubRoute route) {

        route = Objects.isNull(route) ? HubRoute.emptyObject() : route;
        Hub departmentHub = Objects.isNull(route.getDepartmentHub()) ? Hub.emptyObject() : route.getDepartmentHub();
        Hub arriveHub = Objects.isNull(route.getArriveHub()) ? Hub.emptyObject() : route.getArriveHub();

        return new HubRouteVO(
                route.getId(),
                route.getDepartmentAddress(), departmentHub.getId(),
                route.getArriveAddress(), arriveHub.getId(), route.getTakeTime(), route.getRoute(),
            route.getCreatedAt(), route.getUpdatedAt(), route.getDeletedAt(), route.getCreatedBy(), route.getUpdatedBy(), route.getDeletedBy(), route.isDeleted());

    }

}
