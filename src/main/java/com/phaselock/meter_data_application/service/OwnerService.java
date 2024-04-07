package com.phaselock.meter_data_application.service;

import com.phaselock.meter_data_application.dto.entity.create.OwnerCreateDto;
import com.phaselock.meter_data_application.dto.entity.read.OwnerReadDto;
import com.phaselock.meter_data_application.exception.NotFoundException;
import com.phaselock.meter_data_application.mapper.entity.OwnerMapper;
import com.phaselock.meter_data_application.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    public OwnerReadDto saveOwner(OwnerCreateDto ownerCreateDto) {
        return Optional.of(ownerCreateDto)
                .map(ownerMapper::map)
                .map(ownerRepository::save)
                .map(ownerMapper::map)
                .orElseThrow();
    }

    public List<OwnerReadDto> findAll() {
        return ownerRepository.findAll()
                .stream()
                .map(ownerMapper::map)
                .collect(Collectors.toList());
    }

    public Optional<OwnerReadDto> findById(Integer id) {
        return Optional.ofNullable(
                ownerRepository.findById(id)
                        .map(ownerMapper::map)
                        .orElseThrow(() -> new NotFoundException("This owner not exist"))
        );
    }

    public void deleteById(Integer id) {
        ownerRepository.findById(id)
                .ifPresent(account -> ownerRepository.deleteById(id));
    }
}
