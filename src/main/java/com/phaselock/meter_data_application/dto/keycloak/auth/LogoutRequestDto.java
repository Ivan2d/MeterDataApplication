package com.phaselock.meter_data_application.dto.keycloak.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Dto to save information about refresh token")

public class LogoutRequestDto {
    @Schema(description = "Refresh token to logout and refresh access token")
    @JsonProperty(value = "refresh_token")
    private String refreshToken;
}
