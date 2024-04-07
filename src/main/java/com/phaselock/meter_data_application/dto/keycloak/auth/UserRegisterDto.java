package com.phaselock.meter_data_application.dto.keycloak.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dto to registration")
public class UserRegisterDto {
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private UserRoleDto userRoleDto;
}