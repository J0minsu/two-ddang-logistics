package com.two_ddang.logistics.auth.application.service;

import com.two_ddang.logistics.auth.user.UserService;
import com.two_ddang.logistics.auth.user.dto.SignInRequestDto;
import com.two_ddang.logistics.auth.user.dto.SignUpRequestDto;
import com.two_ddang.logistics.auth.user.dto.UserRes;
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

        UserRes responseDto = userService.getUserByUsername(requestDto.username());

        return jwtTokenGenerator.createJwtToken(responseDto.userId(), responseDto.email(),
                responseDto.email(), responseDto.role());
    }

    public void signUp(SignUpRequestDto requestDto) {
        userService.createUser(requestDto);
    }

}
