package com.two_ddang.logistics.auth.controller;

import com.two_ddang.logistics.auth.service.AuthService;
import com.two_ddang.logistics.auth.user.dto.SignInRequestDto;
import com.two_ddang.logistics.auth.user.dto.SignUpRequestDto;
import com.two_ddang.logistics.core.util.ResponseDTO;
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
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<ResponseDTO<Void>> signIn(@RequestBody SignInRequestDto requestDto,
                                                    HttpServletResponse response) {

        return ResponseEntity.ok(ResponseDTO.ok());

    }

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseDTO<Void>> signUp(@RequestBody SignUpRequestDto requestDto) {

        return ResponseEntity.ok(ResponseDTO.ok());

    }

}
