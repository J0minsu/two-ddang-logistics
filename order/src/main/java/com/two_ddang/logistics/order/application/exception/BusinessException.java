package com.two_ddang.logistics.order.application.exception;

import com.two_ddang.logistics.core.exception.ApplicationException;
import com.two_ddang.logistics.core.exception.ErrorCode;

public class BusinessException extends ApplicationException {

    public BusinessException(ErrorCode errorCode) {
        super(errorCode);
    }


    public BusinessException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
