package com.phaselock.meterdataapplication.dto.keycloak;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Dto to save information about token from Keycloak")
public class AccessTokenDto {
    @Schema(description = "time to live of the token in seconds", example = "3600")
    private long expiresIn;

    @Schema(description = "ID token", example = "abc123")
    private String idToken;

    @Schema(description = "time from which the token can be used", example = "1609459200")
    private int notBeforePolicy;

    @Schema(description = "additional token data", example = "{\"key\": \"value\"}")
    private Map<String, Object> otherClaims;

    @Schema(description = "time to live of the refresh token in seconds", example = "7200")
    private long refreshExpiresIn;

    @Schema(description = "token for refreshing")
    private String refreshToken;

    @Schema(description = "access token scope", example = "openid profile")
    private String scope;

    @Schema(description = "session state")
    private String sessionState;

    @Schema(description = "access token")
    private String token;

    @Schema(description = "token type", example = "Bearer")
    private String tokenType;
}

