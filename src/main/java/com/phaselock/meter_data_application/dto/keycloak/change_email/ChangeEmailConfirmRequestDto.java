package com.phaselock.meter_data_application.dto.keycloak.change_email;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;

import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ChangeEmailConfirmRequestDto(
        UUID otpUid,
        String otpCode,
        @Email
        String email,
        String password
) { }
