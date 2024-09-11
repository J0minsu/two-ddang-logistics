package com.two_ddang.logistics.auth.user.dto;

import com.two_ddang.logistics.auth.user.UserRole;

public record UserResponseDto(

        Long userId,
        String username,
        String password,
        String email,
        UserRole role
) {

}
