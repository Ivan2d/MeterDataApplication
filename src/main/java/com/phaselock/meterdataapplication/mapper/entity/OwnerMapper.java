package com.phaselock.meterdataapplication.mapper.entity;

import com.phaselock.meterdataapplication.dto.entity.OwnerCreateDto;
import com.phaselock.meterdataapplication.entity.Apartment;
import com.phaselock.meterdataapplication.entity.Owner;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapper {
    public OwnerCreateDto map(Owner owner) {
        OwnerCreateDto ownerCreateDto = new OwnerCreateDto();
        ownerCreateDto.setFirstName(owner.getFirstName());
        ownerCreateDto.setLastName(owner.getLastName());
        ownerCreateDto.setPhone(owner.getPhone());
        ownerCreateDto.setApartmentId(owner.getApartment().getId());
        return ownerCreateDto;
    }

    public Owner map(OwnerCreateDto ownerCreateDto) {
        Owner owner = new Owner();
        Apartment apartment = new Apartment();
        apartment.setId(ownerCreateDto.getApartmentId());
        owner.setFirstName(ownerCreateDto.getFirstName());
        owner.setLastName(ownerCreateDto.getLastName());
        owner.setPhone(ownerCreateDto.getPhone());
        owner.setApartment(apartment);
        return owner;
    }
}