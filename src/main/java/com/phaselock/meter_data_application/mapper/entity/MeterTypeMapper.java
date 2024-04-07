package com.phaselock.meter_data_application.mapper.entity;

import com.phaselock.meter_data_application.dto.entity.create.MeterTypeCreateDto;
import com.phaselock.meter_data_application.dto.entity.read.MeterTypeReadDto;
import com.phaselock.meter_data_application.entity.MeterType;
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
