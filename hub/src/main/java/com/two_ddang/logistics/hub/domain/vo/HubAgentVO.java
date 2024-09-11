package com.two_ddang.logistics.hub.domain.vo;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class HubAgentVO {

    private final UUID id;
    private final UserVO user;
    private final HubVO hub;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final LocalDateTime deletedAt;
    private final Integer createdBy;
    private final Integer updatedBy;
    private final Integer deletedBy;

    private final boolean isDeleted;
}