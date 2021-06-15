package com.alethio.service.exception;


import com.alethio.service.common.ErrorType;

public abstract class BusinessException extends RuntimeException {

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public abstract ErrorType getErrorType();
}
