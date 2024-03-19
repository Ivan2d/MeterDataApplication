package com.phaselock.meterdataapplication.mapper.entity;

import com.phaselock.meterdataapplication.dto.entity.create.OwnerCreateDto;
import com.phaselock.meterdataapplication.dto.entity.read.OwnerReadDto;
import com.phaselock.meterdataapplication.entity.Owner;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapper {
    public OwnerReadDto map(Owner owner) {
        OwnerReadDto ownerReadDto = new OwnerReadDto();
        ownerReadDto.setFirstName(owner.getFirstName());
        ownerReadDto.setLastName(owner.getLastName());
        ownerReadDto.setPhone(owner.getPhone());
        return ownerReadDto;
    }

    public Owner map(OwnerCreateDto ownerCreateDto) {
        Owner owner = new Owner();
        owner.setFirstName(ownerCreateDto.getFirstName());
        owner.setLastName(ownerCreateDto.getLastName());
        owner.setPhone(ownerCreateDto.getPhone());
        return owner;
    }
}