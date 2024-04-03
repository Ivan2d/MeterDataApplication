package com.phaselock.meterdataapplication.service;

import com.phaselock.meterdataapplication.dto.entity.create.MeterReadingCreateDto;
import com.phaselock.meterdataapplication.dto.entity.read.MeterReadingReadDto;
import com.phaselock.meterdataapplication.entity.MeterReading;
import com.phaselock.meterdataapplication.exception.NotFoundException;
import com.phaselock.meterdataapplication.mapper.entity.MeterReadingMapper;
import com.phaselock.meterdataapplication.repository.MeterReadingRepository;
import com.phaselock.meterdataapplication.repository.MeterRepository;
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
