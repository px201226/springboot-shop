package item.domain.exception.business;


import item.domain.common.ErrorType;
import item.domain.exception.BusinessException;

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
