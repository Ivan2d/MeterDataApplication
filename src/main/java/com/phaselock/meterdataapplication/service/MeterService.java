package com.phaselock.meterdataapplication.service;

import com.phaselock.meterdataapplication.dto.entity.MeterCreateDto;
import com.phaselock.meterdataapplication.entity.Meter;
import com.phaselock.meterdataapplication.exception.not_found_exception.NotFoundException;
import com.phaselock.meterdataapplication.mapper.entity.MeterMapper;
import com.phaselock.meterdataapplication.repository.MeterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeterService {
    private final MeterRepository meterRepository;
    private final MeterMapper meterMapper;

    public MeterCreateDto saveMeter(MeterCreateDto meterCreateDto) {
        return Optional.of(meterCreateDto)
                .map(meterMapper::map)
                .map(meterRepository::save)
                .map(meterMapper::map)
                .orElseThrow();
    }

    public Iterable<Meter> findAll() {
        return meterRepository.findAll();
    }

    public Optional<Meter> findById(Integer id) {
        return Optional.ofNullable(meterRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("This meter not exist")));
    }

    public void deleteById(Integer id) {
        meterRepository.findById(id)
                .ifPresent(account -> meterRepository.deleteById(id));
    }

    public Iterable<Meter> findByApartmentId(Integer id) {
        return meterRepository.findAllByApartmentId(id);
    }

    public Iterable<Meter> findAllMeterByAllOwnerId(Integer id) {
        return meterRepository.findAllByApartmentAndOwnerId(id);
    }
}
