package com.phaselock.meterdataapplication.dto.keycloak;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Dto to login in system")
public class UserAuthDto {
    private String username;
    private String password;
}
