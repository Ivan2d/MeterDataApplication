package com.phaselock.meter_data_application.controller;

import com.phaselock.meter_data_application.entity.Meter;
import com.phaselock.meter_data_application.service.MeterService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/meter")
@RequiredArgsConstructor
@SecurityRequirement(name = "Keycloak")
public class UserAndOwnerRestController {
    private final MeterService meterService;

    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public List<Meter> getMeterByApartmentId(@PathVariable("id") Integer id) {
        return meterService.findByApartmentId(id);
    }

    @GetMapping("/owners/{id}")
    @PreAuthorize("hasRole('owner') or hasRole('admin')")
    public List<Meter> getMeterByOwnerId(@PathVariable("id") Integer id) {
        return meterService.findAllMeterByAllOwnerId(id);
    }
}
