package com.phaselock.meterdataapplication.controller.keycloak;

import com.phaselock.meterdataapplication.dto.keycloak.UserAuthDto;
import com.phaselock.meterdataapplication.dto.keycloak.UserRegisterDto;
import com.phaselock.meterdataapplication.dto.keycloak.AccessTokenDto;
import com.phaselock.meterdataapplication.dto.keycloak.LogoutRequestDto;
import com.phaselock.meterdataapplication.dto.keycloak.LogoutResponseDto;
import com.phaselock.meterdataapplication.exception.NotFoundException;
import com.phaselock.meterdataapplication.service.keycloak.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@SecurityRequirement(name = "Keycloak")
public class AuthRestController {
    private final AuthService authService;


    @PostMapping("/register")
    public void registerUser(@RequestBody UserRegisterDto userRegisterDto) {
        authService.addUser(userRegisterDto);
    }

    @Operation(
            summary = "Login user.",
            operationId = "loginUser",
            description = "Login user.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok",
                            content = {
                                    @Content(mediaType = "application/json", schema =
                                    @Schema(implementation = AccessTokenDto.class))
                            }),
                    @ApiResponse(responseCode = "400", description = "Bad request",
                            content = {
                                    @Content(mediaType = "application/json", schema =
                                    @Schema(implementation = NotFoundException.class))
                            }),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = {
                                    @Content(mediaType = "application/json", schema =
                                    @Schema(implementation = Exception.class))
                            }
                    )
            }
    )
    @PostMapping("/login")
    public AccessTokenDto loginUser(@RequestBody UserAuthDto userAuthDto) {
       return authService.loginUser(userAuthDto);
    }

    @Operation(
            summary = "Logout user.",
            operationId = "logoutUser",
            description = "Logout user.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok",
                            content = {
                                    @Content(mediaType = "application/json", schema =
                                    @Schema(implementation = LogoutRequestDto.class))
                            }),
                    @ApiResponse(responseCode = "400", description = "Bad request",
                            content = {
                                    @Content(mediaType = "application/json", schema =
                                    @Schema(implementation = NotFoundException.class))
                            }),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = {
                                    @Content(mediaType = "application/json", schema =
                                    @Schema(implementation = Exception.class))
                            }
                    )
            }
    )
    @PostMapping("/logout")
    public LogoutResponseDto logoutUser(@RequestBody LogoutRequestDto logoutRequestDto) {
        return authService.logoutUser(logoutRequestDto);
    }
}
