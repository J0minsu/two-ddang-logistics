package com.two_ddang.logistics.auth.user.dto;

import com.two_ddang.logistics.core.entity.UserType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRes {

    private Integer userId;

    private String username;

    private String email;

    private UserType role;

    private String name;

    private UUID slackId;

    public static UserRes example() {
        return new UserRes(1, "혼긴돌", "a@a.b", UserType.HUB, "혼의의지", UUID.randomUUID());
    }

}
