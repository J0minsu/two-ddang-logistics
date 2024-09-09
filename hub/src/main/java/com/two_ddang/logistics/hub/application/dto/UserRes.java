package com.two_ddang.logistics.hub.application.dto;

import com.two_ddang.logistics.hub.domain.model.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@Schema(title = "사용자 응답 DTO")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRes {

    private Integer userId;
    private String username;
    private String email;
    private UserType role;
    private String nickname;
    private UUID slackId;

    public static UserRes fromVO(
            Integer userId, String username,
            String email, UserType role, String nickname, UUID slackId
    ) {
        return new UserRes(userId, username, email, role, nickname, slackId);
    }

    public static UserRes example() {
        return new UserRes(1, "혼긴돌", "a@a.b", UserType.HUB, "혼의의지", UUID.randomUUID());
    }

}
