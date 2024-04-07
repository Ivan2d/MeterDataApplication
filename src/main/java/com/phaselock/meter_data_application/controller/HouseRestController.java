package com.phaselock.meter_data_application.controller;

import com.phaselock.meter_data_application.dto.entity.create.HouseCreateDto;
import com.phaselock.meter_data_application.dto.entity.read.HouseReadDto;
import com.phaselock.meter_data_application.exception.NotFoundException;
import com.phaselock.meter_data_application.service.HouseService;
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
@RequestMapping("/api/v1/houses")
@RequiredArgsConstructor
@SecurityRequirement(name = "Keycloak")
public class HouseRestController {
    private final HouseService houseService;

    @Operation(
            summary = "Creating a house.",
            operationId = "createHouse",
            description = "Creating a house.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok",
                            content = {
                                    @Content(mediaType = "application/json", schema =
                                    @Schema(implementation = HouseReadDto.class))
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
    public HouseReadDto addHouse(@RequestBody HouseCreateDto houseCreateDto) {
        return houseService.saveHouse(houseCreateDto);
    }

    @Operation(
            summary = "Find all houses.",
            operationId = "findAllHouses",
            description = "Find all houses.",
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
    public List<HouseReadDto> getAllHouses() {
        return houseService.findAll();
    }

    @Operation(
            summary = "Find house by Id.",
            operationId = "findHouseById",
            description = "Find house by Id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok",
                            content = {
                                    @Content(mediaType = "application/json", schema =
                                    @Schema(implementation = HouseReadDto.class))
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
    public Optional<HouseReadDto> getHouseById(@PathVariable("id") Integer id) throws NotFoundException {
        return houseService.findById(id);
    }

    @Operation(
            summary = "Delete house by Id.",
            operationId = "deleteHouseById",
            description = "Delete house by Id.",
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
    public void deleteHouseById(@PathVariable("id") Integer id) {
        houseService.deleteById(id);
    }
}
