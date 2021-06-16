package com.alethio.service.exception.business;


import com.alethio.service.common.ErrorType;
import com.alethio.service.exception.BusinessException;

// 아이템을 찾을 수 없을 때 발생하는 예외
public class NoSuchItemException extends BusinessException {

    @Override
    public ErrorType getErrorType() {
        return ErrorType.NO_SUCH_ITEM;
    }
}
