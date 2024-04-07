package com.phaselock.meter_data_application.dto.entity.read;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.phaselock.meter_data_application.entity.NotificationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dto to read notification entity.")
public class NotificationReadDto {
    @Schema(description = "Notification id", example = "1")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Notification name", example = "Attention!")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Notification name", example = "Please, pay for gas")
    @JsonProperty("description")
    private String description;

    @Schema(description = "Notification type", example = "Please, pay for gas")
    @JsonProperty("type")
    private NotificationType notificationType;

    @Schema(description = "Notification created at time", example = "2022-01-20 10:12:30")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @Schema(description = "Email where notification will send", example = "test@gmail.com")
    @JsonProperty("email")
    private String email;
}