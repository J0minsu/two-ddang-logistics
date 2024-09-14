package com.two_ddang.logistics.auth.user.dto;

import com.two_ddang.logistics.core.entity.UserType;

import java.util.UUID;

public record SignUpRequestDto(
        String username,
        String password,
        String name,
        String contact,
        String email,
        UserType role,
        UUID slackId
) {

}
