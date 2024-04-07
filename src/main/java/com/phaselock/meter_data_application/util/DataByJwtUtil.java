package com.phaselock.meter_data_application.util;

import org.springframework.security.oauth2.jwt.Jwt;

public class DataByJwtUtil {

    private static final String USERNAME_CLAIM = "preferred_username";

    public static String getUsernameJwtClaim(Jwt jwtToken) {
        return jwtToken.getClaim(USERNAME_CLAIM);
    }
}