package order.domain.exception;

import order.domain.common.ErrorType;

public abstract class BusinessException extends RuntimeException {

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public abstract ErrorType getErrorType();
}
