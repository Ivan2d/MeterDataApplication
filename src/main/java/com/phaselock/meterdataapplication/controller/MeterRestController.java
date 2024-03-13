package com.phaselock.meterdataapplication.controller;

import com.phaselock.meterdataapplication.dto.entity.MeterCreateDto;
import com.phaselock.meterdataapplication.entity.Meter;
import com.phaselock.meterdataapplication.exception.not_found_exception.NotFoundException;
import com.phaselock.meterdataapplication.service.MeterService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/meters")
@RequiredArgsConstructor
@SecurityRequirement(name = "Keycloak")
public class MeterRestController {

    private final MeterService meterService;

    @PostMapping("")
    @PreAuthorize("hasRole('user') or hasRole('owner') or hasRole('admin')")
    public MeterCreateDto addMeter(@RequestBody MeterCreateDto meterCreateDto) {
        return meterService.saveMeter(meterCreateDto);
    }

    @GetMapping("")
    @PreAuthorize("hasRole('admin')")
    public Iterable<Meter> getAllMeters() {
        return meterService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public Optional<Meter> getMeterById(@PathVariable("id") Integer id) throws NotFoundException {
        return meterService.findById(id);
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public void deleteMeterById(@PathVariable("id") Integer id) {
        meterService.deleteById(id);
    }

}
