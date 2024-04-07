package com.phaselock.meter_data_application.dto.entity.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.phaselock.meter_data_application.entity.NotificationType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dto to create notification entity.")
public class NotificationCreateDto {
    @Schema(description = "Notification name", example = "Attention!")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Notification name", example = "Please, pay for gas")
    @JsonProperty("description")
    private String description;

    @Schema(description = "Notification type", example = "Please, pay for gas")
    @JsonProperty("type")
    private NotificationType notificationType;

    @Email
    @Schema(description = "Email where notification will send", example = "test@gmail.com")
    @JsonProperty("email")
    private String email;
}