package com.phaselock.meterdataapplication.mapper.entity;

import com.phaselock.meterdataapplication.dto.entity.ApartmentCreateDto;
import com.phaselock.meterdataapplication.entity.Apartment;
import org.springframework.stereotype.Component;

@Component
public class ApartmentMapper {
    public ApartmentCreateDto map(Apartment apartment) {
        ApartmentCreateDto apartmentCreateDto = new ApartmentCreateDto();
        apartmentCreateDto.setAddress(apartment.getAddress());
        apartmentCreateDto.setApartmentNumber(apartment.getApartmentNumber());
        apartmentCreateDto.setArea(apartment.getArea());
        return apartmentCreateDto;
    }

    public Apartment map(ApartmentCreateDto apartmentCreateDto) {
        Apartment apartment = new Apartment();
        apartment.setAddress(apartmentCreateDto.getAddress());
        apartment.setApartmentNumber(apartmentCreateDto.getApartmentNumber());
        apartment.setArea(apartmentCreateDto.getArea());
        return apartment;
    }
}
