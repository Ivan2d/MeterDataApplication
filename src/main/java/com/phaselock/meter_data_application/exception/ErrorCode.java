package com.phaselock.meter_data_application.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INCORRECT_NOTIFICATION_ID("Incorrect notification id");

    private final String name;

    ErrorCode(String name) {
        this.name = name;
    }
}