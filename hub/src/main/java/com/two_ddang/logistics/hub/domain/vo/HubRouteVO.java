package com.two_ddang.logistics.hub.domain.vo;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class HubRouteVO {

    private final UUID id;
    private final String departmentAddress;
    private final HubVO departmentHub;
    private final String arriveAddress;
    private final HubVO arriveHub;
    private final int takeTime;
    private final String route;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final LocalDateTime deletedAt;
    private final Integer createdBy;
    private final Integer updatedBy;
    private final Integer deletedBy;

    private final boolean isDeleted;
}
