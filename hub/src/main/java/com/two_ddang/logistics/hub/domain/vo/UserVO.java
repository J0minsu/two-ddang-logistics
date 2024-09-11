package com.two_ddang.logistics.hub.domain.vo;

import com.two_ddang.logistics.core.entity.UserType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class UserVO {

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

}
