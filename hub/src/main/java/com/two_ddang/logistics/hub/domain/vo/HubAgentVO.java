package com.two_ddang.logistics.hub.domain.vo;

import com.two_ddang.logistics.hub.domain.model.Hub;
import com.two_ddang.logistics.hub.domain.model.HubAgent;
import com.two_ddang.logistics.hub.domain.model.User;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class HubAgentVO implements Serializable {

    private final UUID id;
    private final Integer userId;
    private final UUID hubId;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final LocalDateTime deletedAt;
    private final Integer createdBy;
    private final Integer updatedBy;
    private final Integer deletedBy;

    private final boolean isDeleted;

    public static HubAgentVO fromEntity(HubAgent agent) {

        agent = Objects.isNull(agent) ? HubAgent.emptyObject() : agent;
        User user = Objects.isNull(agent.getUser()) ? User.emptyObject() : agent.getUser();
        Hub hub = Objects.isNull(agent.getHub()) ? Hub.emptyObject() : agent.getHub();

        return new HubAgentVO(agent.getId(), user.getId(), hub.getId(),
            agent.getCreatedAt(), agent.getUpdatedAt(), agent.getDeletedAt(), agent.getCreatedBy(), agent.getUpdatedBy(), agent.getDeletedBy(), agent.isDeleted());
    }

}