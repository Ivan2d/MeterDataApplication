package com.phaselock.meterdataapplication.mapper.entity;

import com.phaselock.meterdataapplication.dto.entity.MeterCreateDto;
import com.phaselock.meterdataapplication.entity.Apartment;
import com.phaselock.meterdataapplication.entity.Meter;
import org.springframework.stereotype.Component;

@Component
public class MeterMapper {
    public MeterCreateDto map(Meter meter) {
        MeterCreateDto meterCreateDto = new MeterCreateDto();
        meterCreateDto.setType(meter.getType());
        meterCreateDto.setReading(meter.getReading());
        meterCreateDto.setInstallationDate(meter.getInstallationDate());
        meterCreateDto.setApartmentId(meter.getApartment().getId());
        return meterCreateDto;
    }

    public Meter map(MeterCreateDto meterCreateDto) {
        Meter meter = new Meter();
        Apartment apartment = new Apartment();
        apartment.setId(meterCreateDto.getApartmentId());
        meter.setType(meterCreateDto.getType());
        meter.setReading(meterCreateDto.getReading());
        meter.setInstallationDate(meterCreateDto.getInstallationDate());
        meter.setApartment(apartment);
        return meter;
    }
}