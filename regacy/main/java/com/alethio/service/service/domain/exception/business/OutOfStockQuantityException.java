package com.alethio.service.service.domain.exception.business;


import com.alethio.service.service.domain.common.ErrorType;
import com.alethio.service.service.domain.exception.BusinessException;

// 재고가 부족할 때 발생하는 예외
public class OutOfStockQuantityException extends BusinessException {

    public OutOfStockQuantityException() {
    }

    public OutOfStockQuantityException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.OUT_OF_STOCK_QUANTITY;
    }

}
