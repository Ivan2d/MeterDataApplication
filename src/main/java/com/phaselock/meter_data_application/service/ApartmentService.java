package com.phaselock.meter_data_application.service;

import com.phaselock.meter_data_application.dto.entity.create.ApartmentCreateDto;
import com.phaselock.meter_data_application.dto.entity.read.ApartmentReadDto;
import com.phaselock.meter_data_application.exception.NotFoundException;
import com.phaselock.meter_data_application.mapper.entity.ApartmentMapper;
import com.phaselock.meter_data_application.repository.ApartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final ApartmentMapper apartmentMapper;
    public ApartmentReadDto saveApartment(ApartmentCreateDto apartmentCreateDto) {
        return Optional.of(apartmentCreateDto)
                .map(apartmentMapper::map)
                .map(apartmentRepository::save)
                .map(apartmentMapper::map)
                .orElseThrow();
    }

    public List<ApartmentReadDto> findAll() {
        return apartmentRepository.findAll()
                .stream()
                .map(apartmentMapper::map)
                .collect(Collectors.toList());
    }

    public Optional<ApartmentReadDto> findById(Integer id) {
        return Optional.ofNullable(apartmentRepository.findById(id)
                .map(apartmentMapper::map)
                .orElseThrow(() -> new NotFoundException("This apartment not exist")));
    }

    public void deleteById(Integer id) {
        apartmentRepository.findById(id)
                .ifPresent(account -> apartmentRepository.deleteById(id));
    }

}