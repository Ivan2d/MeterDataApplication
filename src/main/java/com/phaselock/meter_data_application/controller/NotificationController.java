package com.phaselock.meter_data_application.controller;

import com.phaselock.meter_data_application.dto.entity.create.NotificationCreateDto;
import com.phaselock.meter_data_application.dto.entity.read.NotificationReadDto;
import com.phaselock.meter_data_application.entity.NotificationType;
import com.phaselock.meter_data_application.service.NotificationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
@SecurityRequirement(name = "Keycloak")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('user') or hasRole('owner') or hasRole('admin')")
    public NotificationReadDto createNotification(@RequestBody NotificationCreateDto notificationCreateDto) {
        return notificationService.createNotification(notificationCreateDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('user') or hasRole('owner') or hasRole('admin')")
    public NotificationReadDto findNotificationById(@PathVariable Long id) {
        return notificationService.findNotificationById(id);
    }

    @GetMapping("")
    @PreAuthorize("hasRole('user') or hasRole('owner') or hasRole('admin')")
    public List<NotificationReadDto> findAllNotificationsByFilters(
            @RequestParam(value = "type", required = false) List<NotificationType> notificationTypes,
            @RequestParam(value = "email", required = false) List<String> emails,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        return notificationService.findNotificationsByFilters(notificationTypes, emails, page, size);
    }
}