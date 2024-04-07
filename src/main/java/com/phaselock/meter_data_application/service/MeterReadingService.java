package com.phaselock.meter_data_application.service;

import com.phaselock.meter_data_application.dto.entity.create.MeterReadingCreateDto;
import com.phaselock.meter_data_application.dto.entity.read.MeterReadingReadDto;
import com.phaselock.meter_data_application.entity.MeterReading;
import com.phaselock.meter_data_application.exception.NotFoundException;
import com.phaselock.meter_data_application.mapper.entity.MeterReadingMapper;
import com.phaselock.meter_data_application.repository.MeterReadingRepository;
import com.phaselock.meter_data_application.repository.MeterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeterReadingService {
    private final MeterRepository meterRepository;
    private final MeterReadingRepository meterReadingRepository;
    private final MeterReadingMapper meterReadingMapper;

    public Optional<MeterReadingReadDto> saveMeterReading(MeterReadingCreateDto meterReadingCreateDto) {
        return Optional.ofNullable(meterRepository.findById(meterReadingCreateDto.getMeterId())
                .map(meter -> {
                    MeterReading meterReading = meterReadingMapper.map(meterReadingCreateDto);
                    meterReading.setMeter(meter);
                    return meterReadingMapper.map(meterReadingRepository.save(meterReading));
                })
                .orElseThrow(() -> new NotFoundException("This meter does not exist")));
    }


    public List<MeterReadingReadDto> findAll() {
        return meterReadingRepository.findAll().stream()
                .map(meterReadingMapper::map)
                .collect(Collectors.toList());
    }

    public Optional<MeterReadingReadDto> findById(Integer id) {
        return Optional.ofNullable(meterReadingRepository.findById(id)
                        .map(meterReadingMapper::map)
                .orElseThrow(() -> new NotFoundException("This meter reading not exist")));
    }

    public void deleteById(Integer id) {
        meterReadingRepository.findById(id)
                .ifPresent(account -> meterReadingRepository.deleteById(id));
    }
}
