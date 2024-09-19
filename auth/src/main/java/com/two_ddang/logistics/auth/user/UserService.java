package com.two_ddang.logistics.auth.user;

import com.two_ddang.logistics.auth.user.dto.UserRegisterRequest;
import com.two_ddang.logistics.auth.user.dto.UserRes;
import com.two_ddang.logistics.core.util.ResponseDTO;

public interface UserService {

    ResponseDTO<UserRes> getUserByUsername(String username);

    ResponseDTO<UserRes> createUser(UserRegisterRequest requestDto);

}
