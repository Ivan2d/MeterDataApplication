package com.phaselock.meterdataapplication.dto.entity.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.phaselock.meterdataapplication.entity.NotificationType;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationCreateDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("type")
    private NotificationType notificationType;

    @Email
    @JsonProperty("email")
    private String email;
}