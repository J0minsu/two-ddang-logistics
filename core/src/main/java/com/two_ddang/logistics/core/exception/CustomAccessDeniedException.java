package com.two_ddang.logistics.core.exception;

public class CustomAccessDeniedException extends ApplicationException {

    public CustomAccessDeniedException(ErrorCode errorCode) {
        super(errorCode);
    }

}
