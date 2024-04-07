package com.phaselock.meter_data_application.dto.keycloak.change_email;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ChangeEmailInitiateResponseDto(
        UUID otpUid
) { }
