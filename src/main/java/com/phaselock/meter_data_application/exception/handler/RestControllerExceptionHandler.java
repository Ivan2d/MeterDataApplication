package com.phaselock.meter_data_application.exception.handler;

import com.phaselock.meter_data_application.exception.IncorrectPasswordException;
import com.phaselock.meter_data_application.exception.NotFoundException;
import com.phaselock.meter_data_application.exception.NotificationException;
import com.phaselock.meter_data_application.exception.OtpCheckException;
import com.phaselock.meter_data_application.util.ResponseEntityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(basePackages = "com.phaselock.meter_data_application.controller")
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({NotFoundException.class, NotificationException.class})
    public ResponseEntity<?> handleNotFoundExceptionExceptions(NotFoundException exception) {
        return ResponseEntityUtil.responseResultGenerate(
                HttpStatus.BAD_REQUEST, exception.getMessage()
        );
    }

    @ExceptionHandler({IncorrectPasswordException.class, OtpCheckException.class})
    public ResponseEntity<?> handleNotificationException(Exception exception) {
        return ResponseEntityUtil.responseResultGenerate(
                HttpStatus.BAD_REQUEST, exception.getMessage());
    }
}
