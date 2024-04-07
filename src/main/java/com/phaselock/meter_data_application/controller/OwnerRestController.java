package com.phaselock.meter_data_application.controller;

import com.phaselock.meter_data_application.dto.entity.create.OwnerCreateDto;
import com.phaselock.meter_data_application.dto.entity.read.OwnerReadDto;
import com.phaselock.meter_data_application.exception.NotFoundException;
import com.phaselock.meter_data_application.service.OwnerService;
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
@RequestMapping("/api/v1/owners")
@RequiredArgsConstructor
@SecurityRequirement(name = "Keycloak")
public class OwnerRestController {

    private final OwnerService ownerService;

    @Operation(
            summary = "Creating a owner.",
            operationId = "createOwner",
            description = "Creating a owner.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok",
                            content = {
                                    @Content(mediaType = "application/json", schema =
                                    @Schema(implementation = OwnerReadDto.class))
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
    @PreAuthorize("hasRole('owner') OR hasRole('admin')")
    public OwnerReadDto addOwner(@RequestBody OwnerCreateDto ownerCreateDto) {
        return ownerService.saveOwner(ownerCreateDto);
    }

    @Operation(
            summary = "Find all owners.",
            operationId = "findAllOwners",
            description = "Find all owners.",
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
    public List<OwnerReadDto> getAllMeters() {
        return ownerService.findAll();
    }

    @Operation(
            summary = "Find owner by Id.",
            operationId = "findOwnerById",
            description = "Find owner by Id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok",
                            content = {
                                    @Content(mediaType = "application/json", schema =
                                    @Schema(implementation = OwnerReadDto.class))
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
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public Optional<OwnerReadDto> getMeterById(@PathVariable("id") Integer id) throws NotFoundException {
        return ownerService.findById(id);
    }

    @Operation(
            summary = "Delete owner by Id.",
            operationId = "deleteOwnerById",
            description = "Delete owner by Id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok",
                            content = {
                                    @Content(mediaType = "application/json")
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
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public void deleteMeterById(@PathVariable("id") Integer id) {
        ownerService.deleteById(id);
    }
}
