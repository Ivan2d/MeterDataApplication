package com.phaselock.meterdataapplication.util;

import org.springframework.security.oauth2.jwt.Jwt;

import static com.phaselock.meterdataapplication.config.keycloak.KeycloakConfiguration.USERNAME_CLAIM;

public class DataByJwtUtil {

    public static String getUsernameJwtClaim(Jwt jwtToken) {
        return jwtToken.getClaim(USERNAME_CLAIM);
    }
}