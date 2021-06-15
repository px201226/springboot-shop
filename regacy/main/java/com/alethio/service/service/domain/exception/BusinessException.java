package com.alethio.service.service.domain.exception;

import com.alethio.service.service.domain.common.ErrorType;

public abstract class BusinessException extends RuntimeException {

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public abstract ErrorType getErrorType();
}
