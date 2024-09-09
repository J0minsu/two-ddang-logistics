package com.two_ddang.logistics.ai.infrastructure.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // COMMON
    NOT_FOUND(HttpStatus.NOT_FOUND, "경로가 올바르지 않습니다");

    private final HttpStatus httpStatus;

    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
