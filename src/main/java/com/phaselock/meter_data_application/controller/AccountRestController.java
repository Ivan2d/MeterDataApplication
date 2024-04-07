package com.phaselock.meter_data_application.controller;

import com.phaselock.meter_data_application.dto.entity.create.AccountCreateDto;
import com.phaselock.meter_data_application.dto.entity.read.AccountReadDto;
import com.phaselock.meter_data_application.exception.NotFoundException;
import com.phaselock.meter_data_application.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
@SecurityRequirement(name = "Keycloak")
public class AccountRestController {
    private final AccountService accountService;


    @Operation(
            summary = "Creating a account.",
            operationId = "createAccount",
            description = "Creating a account.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok",
                            content = {
                                    @Content(mediaType = "application/json", schema =
                                    @Schema(implementation = AccountReadDto.class))
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
    @PostMapping("")
    @PreAuthorize("hasRole('user') or hasRole('owner') or hasRole('admin')")
    public AccountReadDto addAccount(@RequestBody AccountCreateDto accountCreateDto) {
        return accountService.saveAccount(accountCreateDto);
    }


    @Operation(
            summary = "Find all accounts.",
            operationId = "findAllAccounts",
            description = "Find all accounts.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok",
                            content = {
                                    @Content(mediaType = "application/json", schema =
                                    @Schema(implementation = List.class))
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
    @GetMapping("")
    @PreAuthorize("hasRole('admin')")
    public List<AccountReadDto> getAllAccounts() {
        return accountService.findAll();
    }

    @Operation(
            summary = "Find account by id.",
            operationId = "findAccountById",
            description = "Find account by id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok",
                            content = {
                                    @Content(mediaType = "application/json", schema =
                                    @Schema(implementation = AccountReadDto.class))
                            }),
                    @ApiResponse(responseCode = "400", description = "Bad request - account not found",
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
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public Optional<AccountReadDto> getAccountById(@PathVariable("id") Integer id) throws NotFoundException {
        return accountService.findById(id);
    }


    @Operation(
            summary = "Delete account by id.",
            operationId = "deleteAccountById",
            description = "Delete account by id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok",
                            content = {
                                    @Content(mediaType = "application/json")
                            }),
                    @ApiResponse(responseCode = "400", description = "Bad request - account not found",
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
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public void deleteAccountById(@PathVariable("id") Integer id) {
        accountService.deleteById(id);
    }
}
