package com.phaselock.meter_data_application.dto.keycloak.change_password;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ConfirmChangePasswordDto {
    @NotBlank
    private String email;
    @NotBlank
    private String otpCode;
    @NotNull
    private UUID otpUid;
    @NotBlank
    private String newPassword;
    @NotBlank
    private String confirmNewPassword;
    @NotBlank
    private String destination;
}
