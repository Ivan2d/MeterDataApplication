package com.phaselock.meter_data_application.controller.keycloak;

import com.phaselock.meter_data_application.dto.keycloak.change_password.ChangePasswordDto;
import com.phaselock.meter_data_application.dto.keycloak.change_password.ConfirmChangePasswordDto;
import com.phaselock.meter_data_application.dto.otp.OtpSendResponseDto;
import com.phaselock.meter_data_application.service.keycloak.ChangePasswordService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@SecurityRequirement(name = "Keycloak")
public class ChangePasswordRestController {

    private final ChangePasswordService changePasswordService;
    @PostMapping("/change-password/initiate")
    @PreAuthorize("hasRole('user') or hasRole('owner') or hasRole('admin')")
    public OtpSendResponseDto changePassword(
            @RequestBody @Valid ChangePasswordDto dto,
            @AuthenticationPrincipal Jwt principal
    ) {
        return changePasswordService.initiateChangePassword(dto, principal);
    }

    @PostMapping("/change-password/verify")
    @PreAuthorize("hasRole('user') or hasRole('owner') or hasRole('admin')")
    public void confirmChangePassword(
            @RequestBody @Valid ConfirmChangePasswordDto dto,
            @AuthenticationPrincipal Jwt principal
    ){
        changePasswordService.confirmChangePassword(dto, principal);
    }

}
