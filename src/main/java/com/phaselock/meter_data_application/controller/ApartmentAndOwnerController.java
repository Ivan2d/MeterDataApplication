package com.phaselock.meter_data_application.controller;

import com.phaselock.meter_data_application.dto.entity.create.ApartmentCreateDto;
import com.phaselock.meter_data_application.dto.entity.read.ApartmentReadDto;
import com.phaselock.meter_data_application.dto.entity.read.OwnerReadDto;
import com.phaselock.meter_data_application.service.ApartmentAndOwnerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/owner")
@RequiredArgsConstructor
@SecurityRequirement(name = "Keycloak")
public class ApartmentAndOwnerController {
    private final ApartmentAndOwnerService apartmentAndOwnerService;

    @PostMapping("/apartment/create")
    @PreAuthorize("hasRole('user') or hasRole('owner') or hasRole('admin')")
    public ApartmentReadDto addApartmentAndOwner(@RequestBody ApartmentCreateDto createDto,
                                                 @AuthenticationPrincipal Jwt token) {
        return apartmentAndOwnerService.addApartment(createDto, token);
    }
    @GetMapping("/apartment/{id}")
    @PreAuthorize("hasRole('admin')")
    public Optional<List<OwnerReadDto>> findAllOwnersByApartmentId(@PathVariable("id") Integer id) {
        return apartmentAndOwnerService.findAllOwnersByApartmentId(id);
    }

    @GetMapping("/{id}/apartment")
    @PreAuthorize("hasRole('admin')")
    public Optional<List<ApartmentReadDto>> findAllApartmentsByOwnerId(@PathVariable("id") Integer id) {
        return apartmentAndOwnerService.findAllApartmentsByOwnerId(id);
    }

    @DeleteMapping("/apartment/{id}")
    @PreAuthorize("hasRole('admin')")
    public void deleteApartmentAndOwnerById(@PathVariable("id") Integer id) {
        apartmentAndOwnerService.deleteById(id);
    }
}
