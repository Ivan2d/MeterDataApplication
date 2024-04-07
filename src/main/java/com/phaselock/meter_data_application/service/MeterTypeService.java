package com.phaselock.meter_data_application.service;

import com.phaselock.meter_data_application.dto.entity.read.MeterTypeReadDto;
import com.phaselock.meter_data_application.mapper.entity.MeterTypeMapper;
import com.phaselock.meter_data_application.repository.MeterTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeterTypeService {
    private final MeterTypeRepository meterTypeRepository;
    private final MeterTypeMapper meterTypeMapper;
    public Optional<MeterTypeReadDto> findById(Integer id) {
        return meterTypeRepository.findById(id)
                .map(meterTypeMapper::map);
    }
}
