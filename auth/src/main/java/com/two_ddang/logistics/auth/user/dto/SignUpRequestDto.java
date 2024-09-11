package com.two_ddang.logistics.auth.user.dto;

import com.two_ddang.logistics.auth.user.UserRole;

public record SignUpRequestDto(
        String username,
        String password,
        String email,
        String slackUUID,
        UserRole role
) {

}
