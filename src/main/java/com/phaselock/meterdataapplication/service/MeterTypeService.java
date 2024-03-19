package com.phaselock.meterdataapplication.service;

import com.phaselock.meterdataapplication.dto.entity.read.MeterTypeReadDto;
import com.phaselock.meterdataapplication.mapper.entity.MeterTypeMapper;
import com.phaselock.meterdataapplication.repository.MeterTypeRepository;
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
