package com.two_ddang.logistics.auth.user.dto;

import com.two_ddang.logistics.core.entity.UserType;

import java.util.UUID;

public record UserResponseDto(

        Integer userId,
        String username,
        String email,
        UserType role,
        String name,
        UUID slackId
) {

}
