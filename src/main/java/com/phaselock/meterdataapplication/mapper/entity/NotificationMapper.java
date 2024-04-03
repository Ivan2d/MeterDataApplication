package com.phaselock.meterdataapplication.mapper.entity;

import com.phaselock.meterdataapplication.dto.entity.read.NotificationReadDto;
import com.phaselock.meterdataapplication.entity.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {
    public NotificationReadDto map(Notification notification) {
        return new NotificationReadDto(
                notification.getId(),
                notification.getName(),
                notification.getDescription(),
                notification.getNotificationType(),
                notification.getCreatedAt(),
                notification.getEmail()
        );
    }
}