package com.two_ddang.logistics.auth.user;

import com.two_ddang.logistics.auth.user.dto.UserRegisterRequest;
import com.two_ddang.logistics.auth.user.dto.UserRes;
import com.two_ddang.logistics.core.util.ResponseDTO;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserFallback implements UserFeignClient {

    private final Throwable cause;

    public UserFallback(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public ResponseDTO<UserRes> getUserByUsername(String username) {

        if (cause instanceof FeignException.NotFound) {
            log.info("UserFallback 메소드에서 오류가 발생했습니다.");
            throw new UserNotFoundException();
        }

        return null;
    }

    @Override
    public ResponseDTO<UserRes> createUser(UserRegisterRequest requestDto) {
        if (cause instanceof FeignException.BadRequest) {
            log.info("UserFallback 메소드에서 오류가 발생했습니다.");
            throw new RegisterFailException();
        }
        return null;
    }

}
