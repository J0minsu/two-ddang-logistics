package com.two_ddang.logistics.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    //User
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    PASSWORD_NOT_MATCHES(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    REGISTER_FAIL(HttpStatus.BAD_REQUEST, "회원가입에 실패했습니다."),

    //ai
    AI_NOT_FOUND(HttpStatus.NOT_FOUND, "ID가 올바르지 않습니다."),

    // COMMON
    NOT_FOUND(HttpStatus.NOT_FOUND, "경로가 올바르지 않습니다"),
    CAN_NOT_ACTION_ROLE(HttpStatus.BAD_REQUEST, "해당 요청애 대한 권한이 없습니다."),
    NOT_ENOUGH_STOCK(HttpStatus.BAD_REQUEST, "재고가 부족합니다."),

    FAILED_DELIVERY_REQ(HttpStatus.BAD_REQUEST,"배송 요청이 실패 했습니다." ),
    FAILED_CREATE_ORDER(HttpStatus.BAD_REQUEST, "주문 생성 실패"),
    COMPANY_NOT_FOUND(HttpStatus.NOT_FOUND,"업체가 존재하지 않습니다."),

    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"서버 오류가 발생했습니다. 잠시후 다시 시도해 주세요."),

    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 상품이 존재 하지 않습니다."),
    COMPANY_PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "업체 상품이 존재 하지 않습니다." ),

    // Delivery
    ALREADY_WORK_OUT(HttpStatus.BAD_REQUEST, "업무 중이지 않습니다."),


    ;
    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
