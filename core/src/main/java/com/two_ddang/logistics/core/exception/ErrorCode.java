package com.two_ddang.logistics.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // COMMON
    NOT_FOUND(HttpStatus.NOT_FOUND, "경로가 올바르지 않습니다"),
    CAN_NOT_ACTION_ROLE(HttpStatus.BAD_REQUEST, "해당 요청애 대한 권한이 없습니다."),
    NOT_ENOUGH_STOCK(HttpStatus.BAD_REQUEST, "재고가 부족합니다.");

    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
