package com.two_ddang.logistics.auth.infrastructure.exception;

import com.two_ddang.logistics.core.exception.ApplicationException;
import com.two_ddang.logistics.core.exception.ErrorCode;

public class PasswordNotMatchException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.USER_NOT_FOUND;

    public PasswordNotMatchException() {
        super(ERROR_CODE);
    }

}
