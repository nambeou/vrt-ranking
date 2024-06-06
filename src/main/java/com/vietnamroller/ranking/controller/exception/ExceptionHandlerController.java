package com.vietnamroller.ranking.controller.exception;

import io.netty.handler.timeout.ReadTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ReadTimeoutException.class)
    public ResponseEntity<ErrorResponse> handleReadTimeoutException(ReadTimeoutException exception) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .error("ERROR_TIMEOUT")
                .errorDescription("Your request has timeout... Please retry.")
                .build(), HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.I_AM_A_TEAPOT);
    }
}
