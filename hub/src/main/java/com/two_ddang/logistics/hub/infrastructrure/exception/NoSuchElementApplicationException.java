package com.two_ddang.logistics.hub.infrastructrure.exception;

import com.two_ddang.logistics.core.exception.ApplicationException;
import com.two_ddang.logistics.core.exception.ErrorCode;
import lombok.Getter;

@Getter
public class NoSuchElementApplicationException extends ApplicationException {

    public NoSuchElementApplicationException() {
        super(ErrorCode.NOT_FOUND);
    }

}
