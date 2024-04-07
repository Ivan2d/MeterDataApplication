package com.phaselock.meter_data_application.mapper.entity;

import com.phaselock.meter_data_application.dto.entity.create.ApartmentCreateDto;
import com.phaselock.meter_data_application.dto.entity.read.ApartmentReadDto;
import com.phaselock.meter_data_application.entity.Apartment;
import com.phaselock.meter_data_application.entity.House;
import org.springframework.stereotype.Component;

@Component
public class ApartmentMapper {
    public ApartmentReadDto map(Apartment apartment) {
        ApartmentReadDto apartmentReadDto = new ApartmentReadDto();
        apartmentReadDto.setApartmentNumber(apartment.getApartmentNumber());
        apartmentReadDto.setArea(apartment.getArea());
        return apartmentReadDto;
    }

    public Apartment map(ApartmentCreateDto apartmentCreateDto) {
        Apartment apartment = new Apartment();
        House house = new House();
        house.setId(apartmentCreateDto.getHouseId());
        apartment.setApartmentNumber(apartmentCreateDto.getApartmentNumber());
        apartment.setArea(apartmentCreateDto.getArea());
        apartment.setHouse(house);
        return apartment;
    }
}
