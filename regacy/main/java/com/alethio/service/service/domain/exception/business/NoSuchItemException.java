package com.alethio.service.service.domain.exception.business;

import com.alethio.service.service.domain.common.ErrorType;
import com.alethio.service.service.domain.exception.BusinessException;

// 아이템을 찾을 수 없을 때 발생하는 예외
public class NoSuchItemException extends BusinessException {

    public NoSuchItemException() {
    }

    public NoSuchItemException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.NO_SUCH_ITEM;
    }
}
