package com.two_ddang.logistics.auth.user;

import com.two_ddang.logistics.auth.user.dto.UserResponseDto;

public interface UserService {

    UserResponseDto getUserByUsername(String username);

}
