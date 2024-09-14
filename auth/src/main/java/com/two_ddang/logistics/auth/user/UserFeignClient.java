package com.two_ddang.logistics.auth.user;

import com.two_ddang.logistics.auth.config.UserFeignClientConfig;
import com.two_ddang.logistics.auth.user.dto.SignUpRequestDto;
import com.two_ddang.logistics.auth.user.dto.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "hub",
configuration = UserFeignClientConfig.class,
fallbackFactory = UserFallbackFactory.class)
public interface UserFeignClient extends UserService {

    @GetMapping("/api/v1/users/{username}")
    UserResponseDto getUserByUsername(@PathVariable("username") String username);

    @PostMapping("/api/v1/users")
    void createUser(SignUpRequestDto requestDto);

}
