package com.phaselock.meter_data_application.exception;

public class IncorrectPasswordException extends RuntimeException{

    public IncorrectPasswordException(String str) {
        super(str);
    }

    public String getCode() {
        return "INCORRECT_PASSWORD";
    }

    public String getMessage() {
        return "Incorrect password, please try again.";
    }
}
