package com.phaselock.meterdataapplication.controller;

import com.phaselock.meterdataapplication.dto.entity.OwnerCreateDto;
import com.phaselock.meterdataapplication.entity.Owner;
import com.phaselock.meterdataapplication.exception.not_found_exception.NotFoundException;
import com.phaselock.meterdataapplication.service.OwnerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/owners")
@RequiredArgsConstructor
@SecurityRequirement(name = "Keycloak")
public class OwnerRestController {

    private final OwnerService ownerService;

    @PostMapping("")
    @PreAuthorize("hasRole('owner')")
    public OwnerCreateDto addMeter(@RequestBody OwnerCreateDto ownerCreateDto) {
        return ownerService.saveOwner(ownerCreateDto);
    }

    @GetMapping("")
    @PreAuthorize("hasRole('admin')")
    public Iterable<Owner> getAllMeters() {
        return ownerService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public Optional<Owner> getMeterById(@PathVariable("id") Integer id) throws NotFoundException {
        return ownerService.findById(id);
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public void deleteMeterById(@PathVariable("id") Integer id) {
        ownerService.deleteById(id);
    }
}
