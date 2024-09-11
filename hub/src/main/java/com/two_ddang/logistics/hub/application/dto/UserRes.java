package com.two_ddang.logistics.hub.application.dto;

import com.two_ddang.logistics.core.entity.UserType;
import com.two_ddang.logistics.hub.domain.vo.UserVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(title = "사용자 응답 DTO")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRes {

    private Integer userId;
    private String username;
    private String email;
    private UserType role;
    private String name;
    private UUID slackId;

    public static UserRes fromVO(UserVO user) {
        return new UserRes(user.getId(), user.getUsername(), user.getEmail(), user.getRole(), user.getName(), user.getSlackId());
    }

    public static UserRes example() {
        return new UserRes(1, "혼긴돌", "a@a.b", UserType.HUB, "혼의의지", UUID.randomUUID());
    }

}
