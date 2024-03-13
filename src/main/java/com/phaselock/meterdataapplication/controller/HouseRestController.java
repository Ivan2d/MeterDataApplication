package com.phaselock.meterdataapplication.controller;

import com.phaselock.meterdataapplication.dto.entity.HouseCreateDto;
import com.phaselock.meterdataapplication.entity.House;
import com.phaselock.meterdataapplication.exception.not_found_exception.NotFoundException;
import com.phaselock.meterdataapplication.service.HouseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/houses")
@RequiredArgsConstructor
@SecurityRequirement(name = "Keycloak")
public class HouseRestController {
    private final HouseService houseService;

    @PostMapping("")
    @PreAuthorize("hasRole('user') or hasRole('owner') or hasRole('admin')")
    public HouseCreateDto addHouse(@RequestBody HouseCreateDto houseCreateDto) {
        return houseService.saveHouse(houseCreateDto);
    }

    @GetMapping("")
    @PreAuthorize("hasRole('admin')")
    public Iterable<House> getAllHouses() {
        return houseService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public Optional<House> getHouseById(@PathVariable("id") Integer id) throws NotFoundException {
        return houseService.findById(id);
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public void deleteHouseById(@PathVariable("id") Integer id) {
        houseService.deleteById(id);
    }
}
