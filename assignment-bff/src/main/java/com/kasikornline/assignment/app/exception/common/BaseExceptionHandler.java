package com.kasikornline.assignment.app.exception.common;

import com.kasikornline.assignment.app.common.model.dto.ErrorResponse;
import com.kasikornline.assignment.app.exception.AccountNotFoundException;
import com.kasikornline.assignment.app.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler({
            UserNotFoundException.class,
            AccountNotFoundException.class
    })
    public ResponseEntity<ErrorResponse> exceptionHandler(RuntimeException e) {
        ErrorResponse errResp = ErrorResponse.builder()
                .message(e.getMessage())
                .httpCode(HttpStatus.NOT_FOUND.value())
                .build();

        return new ResponseEntity<>(errResp, HttpStatus.NOT_FOUND);
    }
}
