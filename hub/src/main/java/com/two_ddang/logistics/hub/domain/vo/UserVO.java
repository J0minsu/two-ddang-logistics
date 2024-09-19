package com.two_ddang.logistics.hub.domain.vo;

import com.two_ddang.logistics.core.entity.UserType;
import com.two_ddang.logistics.hub.domain.model.User;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class UserVO implements Serializable {

    private final Integer id;
    private final String username;
    private final String password;
    private final String name;
    private final String email;
    private final String contact;
    private final UserType role;
    private final UUID slackId;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final LocalDateTime deletedAt;
    private final Integer createdBy;
    private final Integer updatedBy;
    private final Integer deletedBy;

    private final boolean isDeleted;

    public static UserVO fromEntity(User user) {

        user = Objects.isNull(user) ? User.emptyObject() : user;

        return new UserVO(user.getId(), user.getUsername(), null, user.getName(), user.getEmail(), user.getContact(), user.getRole(), user.getSlackId(),
                user.getCreatedAt(), user.getUpdatedAt(), user.getDeletedAt(), user.getCreatedBy(), user.getUpdatedBy(), user.getDeletedBy(), user.isDeleted());

    }

}
