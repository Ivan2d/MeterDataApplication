package com.phaselock.meter_data_application.mapper.entity;

import com.phaselock.meter_data_application.dto.entity.create.MeterCreateDto;
import com.phaselock.meter_data_application.dto.entity.read.MeterReadDto;
import com.phaselock.meter_data_application.entity.Apartment;
import com.phaselock.meter_data_application.entity.Meter;
import com.phaselock.meter_data_application.entity.MeterType;
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