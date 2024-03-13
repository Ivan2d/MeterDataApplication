package com.phaselock.meterdataapplication.exception.handler;

import com.phaselock.meterdataapplication.exception.not_found_exception.NotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(basePackages = "com.phaselock.meterdataapplication.controller")
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundExceptionExceptions(NotFoundException exception) {
        return exception.getMessage();
    }
}
