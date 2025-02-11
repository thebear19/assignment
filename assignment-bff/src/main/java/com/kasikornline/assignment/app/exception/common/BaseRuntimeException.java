package com.kasikornline.assignment.app.exception.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BaseRuntimeException extends RuntimeException {

    private HttpStatus httpStatus;

    private String code;

    public BaseRuntimeException(String message, HttpStatus httpStatus, String code) {
        super(message);
        this.httpStatus = httpStatus;
        this.code = code;
    }
}
