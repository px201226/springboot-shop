package com.alethio.service.service.domain.common;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import java.net.HttpURLConnection;

import static java.net.HttpURLConnection.*;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum  ErrorType {

    NO_SUCH_ITEM(-101,HTTP_NOT_FOUND,"해당 아이템이 존재하지 않습니다."),
    OUT_OF_STOCK_QUANTITY(-102,HTTP_INTERNAL_ERROR, "재고가 부족합니다."),
    ARGUMENT_NOT_VALID(-103,HTTP_BAD_REQUEST,"입력값이 잘못되었습니다.");

    @JsonIgnore
    private int httpStatus;
    private int errorCode;
    private String message;

    ErrorType(int errorCode, int httpStatus, String message ) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.message = message;
    }

}
