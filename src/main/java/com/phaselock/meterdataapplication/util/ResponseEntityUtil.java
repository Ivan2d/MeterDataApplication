package com.phaselock.meterdataapplication.util;

import com.phaselock.meterdataapplication.dto.keycloak.DefaultResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class ResponseEntityUtil {

    public static ResponseEntity<?> responseResultGenerate(HttpStatus status, String message) {
        DefaultResponseDto results = new DefaultResponseDto(
                message,
                status.toString(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(status).body(results);
    }
}