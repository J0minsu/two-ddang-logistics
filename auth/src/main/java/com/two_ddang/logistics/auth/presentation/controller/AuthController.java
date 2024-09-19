package com.two_ddang.logistics.auth.presentation.controller;

import com.two_ddang.logistics.auth.application.service.AuthService;
import com.two_ddang.logistics.auth.user.dto.SignInRequestDto;
import com.two_ddang.logistics.auth.user.dto.UserRegisterRequest;
import com.two_ddang.logistics.core.util.ResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "인증 API")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<ResponseDTO<Void>> signIn(@RequestBody SignInRequestDto requestDto,
                                                    HttpServletResponse response) {

        String token = authService.signIn(requestDto);

        response.setHeader("Authorization", "Bearer "+ token);
        return ResponseEntity.ok(ResponseDTO.ok());

    }

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseDTO<Void>> signUp(@RequestBody UserRegisterRequest requestDto) {

        authService.signUp(requestDto);

        return ResponseEntity.ok(ResponseDTO.ok());

    }

}
