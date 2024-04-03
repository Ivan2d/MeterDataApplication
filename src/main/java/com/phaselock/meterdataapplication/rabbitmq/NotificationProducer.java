package com.phaselock.meterdataapplication.rabbitmq;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.phaselock.meterdataapplication.config.data_adapter.LocalDateTimeAdapter;
import com.phaselock.meterdataapplication.dto.entity.read.NotificationReadDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationProducer {

    @Value("${rabbitmq.exchange.name}")
    private String topicExchange;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;
    private final RabbitTemplate rabbitTemplate;

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();

    public void sendNotification(NotificationReadDto notificationDto) {
        try {
            String notificationJson = gson.toJson(notificationDto);
            rabbitTemplate.convertAndSend(topicExchange, routingKey, notificationJson);
            log.info(String.format("Notification successfully sent to publisher -> %s", notificationDto.getName()));
        } catch (Exception ex) {
            log.error(String.format("Incorrect notification data format -> %s, with exception -> %s", notificationDto, ex.getMessage()));
        }

    }
}