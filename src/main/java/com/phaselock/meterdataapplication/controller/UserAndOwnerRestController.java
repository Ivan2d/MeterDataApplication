package com.phaselock.meterdataapplication.controller;

import com.phaselock.meterdataapplication.entity.Meter;
import com.phaselock.meterdataapplication.service.MeterService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users/meters")
@RequiredArgsConstructor
@SecurityRequirement(name = "Keycloak")
public class UserAndOwnerRestController {
    private final MeterService meterService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public Iterable<Meter> getMeterByApartmentId(@PathVariable("id") Integer id) {
        return meterService.findByApartmentId(id);
    }

    @GetMapping("/owners/{id}")
    @PreAuthorize("hasRole('owner') or hasRole('admin')")
    public Iterable<Meter> getMeterByOwnerId(@PathVariable("id") Integer id) {
        return meterService.findAllMeterByAllOwnerId(id);
    }
}
