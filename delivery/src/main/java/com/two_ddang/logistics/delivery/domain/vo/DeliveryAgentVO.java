package com.two_ddang.logistics.delivery.domain.vo;

import com.two_ddang.logistics.core.entity.DriveStatus;
import com.two_ddang.logistics.core.entity.DriverAgentType;
import com.two_ddang.logistics.delivery.domain.model.DeliveryAgent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DeliveryAgentVO {

    private final UUID id;
    private final Integer userId;
    private final UUID registeredHubId;
    private final DriverAgentType type;
    private final UUID slackId;
    private final DriveStatus driveStatus;
    private final UUID stayHubId;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final LocalDateTime deletedAt;
    private final Integer createdBy;
    private final Integer updatedBy;
    private final Integer deletedBy;
    private final boolean isDeleted;

    public static DeliveryAgentVO fromEntity(DeliveryAgent deliveryAgent) {
        return new DeliveryAgentVO(
                deliveryAgent.getId(), deliveryAgent.getUserId(), deliveryAgent.getRegisteredHubId(),
                deliveryAgent.getType(), deliveryAgent.getSlackId(), deliveryAgent.getDriveStatus(), deliveryAgent.getStayHubId(),
                deliveryAgent.getCreatedAt(), deliveryAgent.getUpdatedAt(), deliveryAgent.getDeletedAt(), deliveryAgent.getCreatedBy(), deliveryAgent.getUpdatedBy(), deliveryAgent.getDeletedBy(), deliveryAgent.isDeleted()
        );
    }

}
