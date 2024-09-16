package com.two_ddang.logistics.hub.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.two_ddang.logistics.core.entity.BaseEntity;
import com.two_ddang.logistics.hub.domain.vo.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Entity(name = "p_hub_agents")
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "transit")
@Comment("허브 담당자")
public class HubAgent extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Comment("허브 담당자 ID")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true
            , foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hub_id", nullable = false
            , foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @JsonIgnore
    private Hub hub;

    private HubAgent(User user, Hub hub) {
        this.user = user;
        this.hub = hub;
    }

    public static HubAgent of(User user, Hub hub) {
        return new HubAgent(user, hub);
    }

    public static HubAgent emptyObject() {
        return new HubAgent();
    }

    public HubAgentVO toVO() {

        UserVO userVO = Optional.ofNullable(user).orElse(User.emptyObject()).toVO();

        return new HubAgentVO(id, userVO, hub.toVO(),
            getCreatedAt(), getUpdatedAt(), getDeletedAt(), getCreatedBy(), getUpdatedBy(), getDeletedBy(), isDeleted());
    }

}