package com.phaselock.meterdataapplication.controller.keycloak;

import com.phaselock.meterdataapplication.dto.keycloak.UserAuthDto;
import com.phaselock.meterdataapplication.dto.keycloak.UserRegisterDto;
import com.phaselock.meterdataapplication.dto.keycloak.AccessTokenDto;
import com.phaselock.meterdataapplication.dto.keycloak.LogoutRequestDto;
import com.phaselock.meterdataapplication.dto.keycloak.LogoutResponseDto;
import com.phaselock.meterdataapplication.service.keycloak.KeycloakService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
//@SecurityRequirement(name = "Keycloak")
public class KeycloakRestController {
    private final KeycloakService keycloakService;

    @PostMapping("/register")
    public void registerUser(@RequestBody UserRegisterDto userRegisterDto) {
        keycloakService.addUser(userRegisterDto);
    }

    @PostMapping("/login")
    public AccessTokenDto loginUser(@RequestBody UserAuthDto userAuthDto) {
       return keycloakService.loginUser(userAuthDto);
    }

    @PostMapping("/logout")
    public LogoutResponseDto logoutUser(@RequestBody LogoutRequestDto logoutRequestDto) {
        return keycloakService.logoutUser(logoutRequestDto);
    }
}
