package com.phaselock.meterdataapplication.mapper.entity;

import com.phaselock.meterdataapplication.dto.entity.create.MeterTypeCreateDto;
import com.phaselock.meterdataapplication.dto.entity.read.MeterTypeReadDto;
import com.phaselock.meterdataapplication.entity.MeterType;
import org.springframework.stereotype.Component;

@Component
public class MeterTypeMapper {
    public MeterTypeReadDto map(MeterType meterType) {
        return new MeterTypeReadDto(meterType.getType());
    }

    public MeterType map(MeterTypeCreateDto meterTypeCreateDto) {
        MeterType meterType = new MeterType();
        meterType.setType(meterTypeCreateDto.getType());
        return meterType;
    }
}
