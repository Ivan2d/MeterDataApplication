package com.phaselock.meterdataapplication.entity;

import lombok.Getter;

@Getter
public enum NotificationType {

    INFO("advertising information"),
    PAYMENT("payment reminder");

    private final String name;

    NotificationType(String name) {
        this.name = name;
    }
}