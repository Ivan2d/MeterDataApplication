package com.phaselock.meterdataapplication.mapper.entity;

import com.phaselock.meterdataapplication.dto.entity.create.ApartmentCreateDto;
import com.phaselock.meterdataapplication.dto.entity.read.ApartmentReadDto;
import com.phaselock.meterdataapplication.entity.Apartment;
import com.phaselock.meterdataapplication.entity.House;
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
