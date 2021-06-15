package order.springboot.controller;


import com.alethio.service.common.ErrorType;
import com.alethio.service.exception.BusinessException;
import order.springboot.common.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(assignableTypes = OrderController.class)
public class OrderControllerAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity handleBusinessException(BusinessException e, WebRequest request){
        return ErrorMessage.of(e.getErrorType(), request, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest request){

        Map<String, String> fieldErrorMap = getFieldErrorMap(e);

        return ErrorMessage.of(ErrorType.ARGUMENT_NOT_VALID, request, fieldErrorMap.toString());
    }

    private Map<String,String> getFieldErrorMap(MethodArgumentNotValidException e){

        Map<String,String> fieldErrorMap = new HashMap<>();

        BindingResult bindingResult = e.getBindingResult();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            fieldErrorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
        }

        return fieldErrorMap;
    }

}
