package com.two_ddang.logistics.delivery.infrastructrure.exception;

import com.two_ddang.logistics.core.exception.ApplicationException;
import com.two_ddang.logistics.core.exception.ErrorCode;
import lombok.Getter;

@Getter
public class AlreadyWorkOutApplicationException extends ApplicationException {

    public AlreadyWorkOutApplicationException() {
        super(ErrorCode.NOT_FOUND);
    }

}
