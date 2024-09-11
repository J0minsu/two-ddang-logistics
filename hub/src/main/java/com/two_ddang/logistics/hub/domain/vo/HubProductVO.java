package com.two_ddang.logistics.hub.domain.vo;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class HubProductVO {

    private final UUID id;
    private final String productName;
    private final UUID productId;
    private final UUID companyId;
    private final int stock;
    private final HubVO hub;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final LocalDateTime deletedAt;
    private final Integer createdBy;
    private final Integer updatedBy;
    private final Integer deletedBy;

    private final boolean isDeleted;

}
