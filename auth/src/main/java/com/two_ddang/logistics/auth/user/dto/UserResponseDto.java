package com.two_ddang.logistics.auth.user.dto;

import com.two_ddang.logistics.core.entity.UserType;

public record UserResponseDto(

        Long userId,
        String username,
        String password,
        String email,
        UserType userType
) {

}
