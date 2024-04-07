package com.phaselock.meter_data_application.dto.keycloak.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Dto-response to check status code")
public class LogoutResponseDto {
    @Schema(description = "status code", example = "200")
    private int response;
}
