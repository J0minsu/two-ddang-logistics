package com.two_ddang.logistics.ai.exception;

import com.two_ddang.logistics.core.exception.ApplicationException;
import com.two_ddang.logistics.core.exception.ErrorCode;

public class AINotFoundException extends ApplicationException {

    private static final ErrorCode errorCode = ErrorCode.AI_NOT_FOUND;

    public AINotFoundException() {
        super(errorCode);
    }

}
