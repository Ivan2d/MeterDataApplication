package com.phaselock.meterdataapplication.exception.handler;

import com.phaselock.meterdataapplication.exception.not_found_exception.NotFoundException;
import com.phaselock.meterdataapplication.util.ResponseEntityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(basePackages = "com.phaselock.meterdataapplication.controller")
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundExceptionExceptions(NotFoundException exception) {
        return ResponseEntityUtil.responseResultGenerate(
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );
    }
}
