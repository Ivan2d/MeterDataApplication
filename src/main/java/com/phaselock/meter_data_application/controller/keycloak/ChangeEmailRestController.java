package com.phaselock.meter_data_application.controller.keycloak;

import com.phaselock.meter_data_application.dto.keycloak.change_email.ChangeEmailConfirmRequestDto;
import com.phaselock.meter_data_application.dto.keycloak.change_email.ChangeEmailConfirmResponseDto;
import com.phaselock.meter_data_application.dto.keycloak.change_email.ChangeEmailInitiateRequestDto;
import com.phaselock.meter_data_application.dto.keycloak.change_email.ChangeEmailInitiateResponseDto;
import com.phaselock.meter_data_application.service.keycloak.ChangeEmailService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/change-email")
@SecurityRequirement(name = "Keycloak")
public class ChangeEmailRestController {

    private final ChangeEmailService changeEmailService;
    @PostMapping(value = "/initiate")
    @PreAuthorize("hasRole('user') or hasRole('owner') or hasRole('admin')")
    ChangeEmailInitiateResponseDto initiate(
            @Valid @RequestBody ChangeEmailInitiateRequestDto request,
            @AuthenticationPrincipal Jwt principal
    ) {
        return changeEmailService.initiate(request.email(), principal);
    }
    @PostMapping(value = "/confirm")
    @PreAuthorize("hasRole('user') or hasRole('owner') or hasRole('admin')")
    ChangeEmailConfirmResponseDto confirm(
            @Valid @RequestBody ChangeEmailConfirmRequestDto request,
            @AuthenticationPrincipal Jwt principal
    ) {
        return changeEmailService.confirm(request, principal);
    }

}