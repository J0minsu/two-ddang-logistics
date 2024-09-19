package com.two_ddang.logistics.auth.application.service;

import com.two_ddang.logistics.auth.user.UserService;
import com.two_ddang.logistics.auth.user.dto.SignInRequestDto;
import com.two_ddang.logistics.auth.user.dto.UserRegisterRequest;
import com.two_ddang.logistics.auth.user.dto.UserRes;
import com.two_ddang.logistics.core.util.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenGenerator jwtTokenGenerator;

    private final UserService userService;

    public String signIn(SignInRequestDto requestDto) {

        ResponseDTO<UserRes> response = userService.getUserByUsername(requestDto.username());

        UserRes responseDto = response.getData();

        return jwtTokenGenerator.createJwtToken(responseDto.getUserId(), responseDto.getEmail(),
                responseDto.getUsername(), responseDto.getRole());
    }

    public void signUp(UserRegisterRequest requestDto) {
        userService.createUser(requestDto);

    }

}
