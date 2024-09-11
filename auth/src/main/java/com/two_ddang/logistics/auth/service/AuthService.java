package com.two_ddang.logistics.auth.service;

import com.two_ddang.logistics.auth.exception.PasswordNotMatchException;
import com.two_ddang.logistics.auth.user.UserService;
import com.two_ddang.logistics.auth.user.dto.SignInRequestDto;
import com.two_ddang.logistics.auth.user.dto.SignUpRequestDto;
import com.two_ddang.logistics.auth.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenGenerator jwtTokenGenerator;

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;
    public String signIn(SignInRequestDto requestDto) {

        UserResponseDto responseDto = userService.getUserByUsername(requestDto.username());
        if (!passwordEncoder.matches(requestDto.password(), responseDto.password())) {
            throw new PasswordNotMatchException();
        }

        return jwtTokenGenerator.createJwtToken(responseDto.userId(), responseDto.email(),
                responseDto.email(), responseDto.userType());
    }

    public void signUp(SignUpRequestDto requestDto) {
        userService.createUser(requestDto);
    }

}
