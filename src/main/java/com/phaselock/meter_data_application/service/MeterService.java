package com.phaselock.meter_data_application.service;

import com.phaselock.meter_data_application.dto.entity.create.MeterCreateDto;
import com.phaselock.meter_data_application.dto.entity.read.MeterReadDto;
import com.phaselock.meter_data_application.entity.Meter;
import com.phaselock.meter_data_application.exception.NotFoundException;
import com.phaselock.meter_data_application.mapper.entity.MeterMapper;
import com.phaselock.meter_data_application.repository.MeterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeterService {
    private final MeterRepository meterRepository;
    private final MeterMapper meterMapper;

    public MeterReadDto saveMeter(MeterCreateDto meterCreateDto) {
        return Optional.of(meterCreateDto)
                .map(meterMapper::map)
                .map(meterRepository::save)
                .map(meterMapper::map)
                .orElseThrow();
    }

    public List<MeterReadDto> findAll() {
        return meterRepository.findAll()
                .stream()
                .map(meterMapper::map)
                .collect(Collectors.toList());
    }

    public Optional<MeterReadDto> findById(Integer id) {
        return Optional.ofNullable(meterRepository.findById(id)
                .map(meterMapper::map)
                .orElseThrow(() -> new NotFoundException("This meter not exist")));
    }

    public void deleteById(Integer id) {
        meterRepository.findById(id)
                .ifPresent(account -> meterRepository.deleteById(id));
    }

    public List<Meter> findByApartmentId(Integer id) {
        return meterRepository.findAllByApartmentId(id);
    }

    public List<Meter> findAllMeterByAllOwnerId(Integer id) {
        return meterRepository.findAllByApartmentAndOwnerId(id);
    }
}
