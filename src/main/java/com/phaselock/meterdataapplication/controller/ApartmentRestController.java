package com.phaselock.meterdataapplication.controller;

import com.phaselock.meterdataapplication.dto.entity.create.ApartmentCreateDto;
import com.phaselock.meterdataapplication.dto.entity.read.ApartmentReadDto;
import com.phaselock.meterdataapplication.exception.NotFoundException;
import com.phaselock.meterdataapplication.service.ApartmentService;
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
@RequestMapping("/api/v1/apartments")
@RequiredArgsConstructor
@SecurityRequirement(name = "Keycloak")
public class ApartmentRestController {
    private final ApartmentService apartmentService;

    @Operation(
            summary = "Creating a apartment.",
            operationId = "createApartment",
            description = "Creating a apartment.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok",
                            content = {
                                    @Content(mediaType = "application/json", schema =
                                    @Schema(implementation = ApartmentReadDto.class))
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
    public ApartmentReadDto addApartment(@RequestBody ApartmentCreateDto apartment) {
       return apartmentService.saveApartment(apartment);
    }


    @Operation(
            summary = "Find all apartments.",
            operationId = "findAllApartments",
            description = "Find all apartments.",
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
    @PreAuthorize("hasAuthority('admin')")
    public List<ApartmentReadDto> getAllApartments() {
        return apartmentService.findAll();
    }

    @Operation(
            summary = "Find apartment by id.",
            operationId = "findApartmentById",
            description = "Find apartment by id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok",
                            content = {
                                    @Content(mediaType = "application/json", schema =
                                    @Schema(implementation = ApartmentReadDto.class))
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
    public Optional<ApartmentReadDto> getApartmentById(@PathVariable("id") Integer id) throws NotFoundException {
        return apartmentService.findById(id);
    }


    @Operation(
            summary = "Delete apartment by id.",
            operationId = "deleteApartmentById",
            description = "Delete apartment by id.",
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
    public void deleteApartmentById(@PathVariable("id") Integer id) {
        apartmentService.deleteById(id);
    }

}
