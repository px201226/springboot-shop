package com.alethio.service.exception;


import com.alethio.service.common.ErrorType;

public abstract class BusinessException extends RuntimeException {

    public abstract ErrorType getErrorType();

}
