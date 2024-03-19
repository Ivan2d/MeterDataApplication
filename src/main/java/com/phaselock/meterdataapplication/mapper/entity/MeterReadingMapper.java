package com.phaselock.meterdataapplication.mapper.entity;

import com.phaselock.meterdataapplication.dto.entity.create.MeterReadingCreateDto;
import com.phaselock.meterdataapplication.dto.entity.read.MeterReadingReadDto;
import com.phaselock.meterdataapplication.entity.Meter;
import com.phaselock.meterdataapplication.entity.MeterReading;
import org.springframework.stereotype.Component;

@Component
public class MeterReadingMapper {

    public MeterReadingReadDto map(MeterReading meterReading) {
        MeterReadingReadDto meterReadingReadDto = new MeterReadingReadDto();
        meterReadingReadDto.setReading(meterReading.getReading());
        meterReadingReadDto.setDateTime(meterReading.getDateTime());
        return meterReadingReadDto;
    }

    public MeterReading map(MeterReadingCreateDto meterReadingCreateDto) {
        MeterReading meterReading = new MeterReading();
        Meter meter = new Meter();
        meter.setId(meterReadingCreateDto.getMeterId());
        meterReading.setReading(meterReadingCreateDto.getReading());
        meterReading.setDateTime(meterReadingCreateDto.getDateTime());
        meterReading.setMeter(meter);
        return meterReading;
    }
}
