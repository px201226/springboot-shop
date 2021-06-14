package stock.domain.exception.business;


import stock.domain.common.ErrorType;
import stock.domain.exception.BusinessException;

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
