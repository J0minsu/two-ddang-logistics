package com.two_ddang.logistics.auth.user.dto;

import com.two_ddang.logistics.core.entity.UserType;

public record SignUpRequestDto(
        String username,
        String password,
        String email,
        String slackUUID,
        UserType userType
) {

}
