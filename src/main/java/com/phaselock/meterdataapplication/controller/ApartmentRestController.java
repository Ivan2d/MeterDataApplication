package com.phaselock.meterdataapplication.controller;

import com.phaselock.meterdataapplication.dto.entity.ApartmentCreateDto;
import com.phaselock.meterdataapplication.entity.Apartment;
import com.phaselock.meterdataapplication.exception.not_found_exception.NotFoundException;
import com.phaselock.meterdataapplication.service.ApartmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/apartments")
@RequiredArgsConstructor
@SecurityRequirement(name = "Keycloak")
public class ApartmentRestController {
    private final ApartmentService apartmentService;

    @PostMapping("")
    @PreAuthorize("hasRole('user') or hasRole('owner') or hasRole('admin')")
    public ApartmentCreateDto addApartment(@RequestBody ApartmentCreateDto apartment) {
       return apartmentService.saveApartment(apartment);
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('admin')")
    public Iterable<Apartment> getAllApartments() {
        return apartmentService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public Optional<Apartment> getApartmentById(@PathVariable("id") Integer id) throws NotFoundException {
        return apartmentService.findById(id);
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public void deleteApartmentById(@PathVariable("id") Integer id) {
        apartmentService.deleteById(id);
    }

}
