package com.phaselock.meterdataapplication.mapper.entity;

import com.phaselock.meterdataapplication.dto.entity.create.MeterCreateDto;
import com.phaselock.meterdataapplication.dto.entity.read.MeterReadDto;
import com.phaselock.meterdataapplication.entity.Apartment;
import com.phaselock.meterdataapplication.entity.Meter;
import com.phaselock.meterdataapplication.entity.MeterType;
import org.springframework.stereotype.Component;

@Component
public class MeterMapper {
    public MeterReadDto map(Meter meter) {
        MeterReadDto meterReadDto = new MeterReadDto();
        meterReadDto.setInstallationDate(meter.getInstallationDate());
        meterReadDto.setEndDate(meter.getEndDate());
        return meterReadDto;
    }

    public Meter map(MeterCreateDto meterCreateDto) {
        Meter meter = new Meter();
        Apartment apartment = new Apartment();
        MeterType meterType = new MeterType();
        apartment.setId(meterCreateDto.getApartmentId());
        meterType.setId(meterCreateDto.getTypeId());
        meter.setInstallationDate(meterCreateDto.getInstallationDate());
        meter.setEndDate(meterCreateDto.getEndDate());
        meter.setApartment(apartment);
        meter.setMeterType(meterType);
        return meter;
    }
}