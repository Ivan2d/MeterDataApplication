package com.phaselock.meter_data_application.mapper.entity;

import com.phaselock.meter_data_application.dto.entity.read.NotificationReadDto;
import com.phaselock.meter_data_application.entity.Notification;
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