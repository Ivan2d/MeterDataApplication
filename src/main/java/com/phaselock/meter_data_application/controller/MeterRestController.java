package com.phaselock.meter_data_application.controller;

import com.phaselock.meter_data_application.dto.entity.create.MeterCreateDto;
import com.phaselock.meter_data_application.dto.entity.read.MeterReadDto;
import com.phaselock.meter_data_application.exception.NotFoundException;
import com.phaselock.meter_data_application.service.MeterService;
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
@RequestMapping("/api/v1/meters")
@RequiredArgsConstructor
@SecurityRequirement(name = "Keycloak")
public class MeterRestController {

    private final MeterService meterService;

    @Operation(
            summary = "Creating a meter.",
            operationId = "createMeter",
            description = "Creating a meter.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok",
                            content = {
                                    @Content(mediaType = "application/json", schema =
                                    @Schema(implementation = MeterReadDto.class))
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
    public MeterReadDto addMeter(@RequestBody MeterCreateDto meterCreateDto) {
        return meterService.saveMeter(meterCreateDto);
    }

    @Operation(
            summary = "Find all meters.",
            operationId = "findAllMeters",
            description = "Find all meters.",
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
    public List<MeterReadDto> getAllMeters() {
        return meterService.findAll();
    }

    @Operation(
            summary = "Find meter by Id.",
            operationId = "findMeterById",
            description = "Find meter by Id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok",
                            content = {
                                    @Content(mediaType = "application/json", schema =
                                    @Schema(implementation = MeterReadDto.class))
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
    public Optional<MeterReadDto> getMeterById(@PathVariable("id") Integer id) throws NotFoundException {
        return meterService.findById(id);
    }

    @Operation(
            summary = "Delete meter by Id.",
            operationId = "deleteMeterById",
            description = "Delete meter by Id.",
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
        meterService.deleteById(id);
    }

}
