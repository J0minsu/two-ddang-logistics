package com.two_ddang.logistics.auth.user;

import com.two_ddang.logistics.auth.user.dto.SignUpRequestDto;
import com.two_ddang.logistics.auth.user.dto.UserRes;

public interface UserService {

    UserRes getUserByUsername(String username);

    void createUser(SignUpRequestDto requestDto);

}
