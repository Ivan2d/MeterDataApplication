package com.phaselock.meter_data_application.service;

import com.phaselock.meter_data_application.dto.entity.create.NotificationCreateDto;
import com.phaselock.meter_data_application.dto.entity.read.NotificationReadDto;
import com.phaselock.meter_data_application.entity.Notification;
import com.phaselock.meter_data_application.entity.NotificationType;
import com.phaselock.meter_data_application.exception.NotificationException;
import com.phaselock.meter_data_application.mapper.entity.NotificationMapper;
import com.phaselock.meter_data_application.rabbitmq.NotificationProducer;
import com.phaselock.meter_data_application.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.phaselock.meter_data_application.exception.ErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private final NotificationProducer notificationProducer;


    public NotificationReadDto createNotification(NotificationCreateDto createDto) {
        Notification notification = Notification
                .builder()
                .name(createDto.getName())
                .description(createDto.getDescription())
                .notificationType(createDto.getNotificationType())
                .createdAt(LocalDateTime.now())
                .email(createDto.getEmail())
                .build();

        Notification result = notificationRepository.save(notification);

        log.info("The message has been saved to the database");

        NotificationReadDto notificationDto = notificationMapper.map(result);

        notificationProducer.sendNotification(notificationDto);

        log.info("The message has been sent to the rabbitmq consumer");
        return notificationDto;
    }


    public NotificationReadDto findNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationException(INCORRECT_NOTIFICATION_ID.getName())
        );

        log.info("The message has been found from the database");
        return notificationMapper.map(notification);
    }

    public List<NotificationReadDto> findNotificationsByFilters(List<NotificationType> notificationTypes,
                                                                List<String> emails,
                                                                Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        if (notificationTypes == null) {
            notificationTypes = new ArrayList<>(Arrays.stream(NotificationType.values()).toList());
        }

        if (emails == null) {
            emails = new ArrayList<>(notificationRepository.findAll()
                    .stream().map(Notification::getEmail).toList());
        }

        List<String> types = notificationTypes.stream().map(Enum::toString).toList();
        List<Notification> notifications = notificationRepository
                .findAllByFilters(types, emails, pageRequest);
        return notifications.stream().map(notificationMapper::map).toList();
    }
}