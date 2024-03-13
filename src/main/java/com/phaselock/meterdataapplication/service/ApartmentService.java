package com.phaselock.meterdataapplication.service;

import com.phaselock.meterdataapplication.dto.entity.ApartmentCreateDto;
import com.phaselock.meterdataapplication.entity.Apartment;
import com.phaselock.meterdataapplication.exception.not_found_exception.NotFoundException;
import com.phaselock.meterdataapplication.mapper.entity.ApartmentMapper;
import com.phaselock.meterdataapplication.repository.ApartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final ApartmentMapper apartmentMapper;
    public ApartmentCreateDto saveApartment(ApartmentCreateDto apartmentCreateDto) {
        return Optional.of(apartmentCreateDto)
                .map(apartmentMapper::map)
                .map(apartmentRepository::save)
                .map(apartmentMapper::map)
                .orElseThrow();
    }

    public Iterable<Apartment> findAll() {
        return apartmentRepository.findAll();
    }

    public Optional<Apartment> findById(Integer id) {
        return Optional.ofNullable(apartmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("This apartment not exist")));
    }

    public void deleteById(Integer id) {
        apartmentRepository.findById(id)
                .ifPresent(account -> apartmentRepository.deleteById(id));
    }

}