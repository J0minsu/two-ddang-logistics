package com.two_ddang.logistics.core.exception;

public class ExpiredInternalTokenException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.EXPIRED_TOKEN;

    public ExpiredInternalTokenException() {
        super(ERROR_CODE);
    }

}
