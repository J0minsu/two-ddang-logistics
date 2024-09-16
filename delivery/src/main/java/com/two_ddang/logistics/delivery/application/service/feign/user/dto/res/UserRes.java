package com.two_ddang.logistics.delivery.application.service.feign.user.dto.res;

import com.two_ddang.logistics.core.entity.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Schema(title = "사용자 조회 DTO")
@AllArgsConstructor
public class UserRes {

    private Integer userId;
    private String username;
    private String email;
    private UserType role;
    private String name;
    private UUID slackId;

}
