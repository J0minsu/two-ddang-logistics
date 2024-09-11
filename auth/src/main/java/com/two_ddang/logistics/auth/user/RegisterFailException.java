package com.two_ddang.logistics.auth.user;

import com.two_ddang.logistics.core.exception.ApplicationException;
import com.two_ddang.logistics.core.exception.ErrorCode;

public class RegisterFailException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.REGISTER_FAIL;

    protected RegisterFailException() {
        super(ERROR_CODE);
    }

}
