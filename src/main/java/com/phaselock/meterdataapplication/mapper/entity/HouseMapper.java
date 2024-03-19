package com.phaselock.meterdataapplication.mapper.entity;

import com.phaselock.meterdataapplication.dto.entity.create.HouseCreateDto;
import com.phaselock.meterdataapplication.dto.entity.read.HouseReadDto;
import com.phaselock.meterdataapplication.entity.House;
import org.springframework.stereotype.Component;

@Component
public class HouseMapper {
    public HouseReadDto map(House house) {
        HouseReadDto houseReadDto = new HouseReadDto();
        houseReadDto.setHouseNumber(house.getHouseNumber());
        houseReadDto.setAddress(house.getAddress());
        return houseReadDto;
    }

    public House map(HouseCreateDto houseCreateDto) {
        House house = new House();
        house.setHouseNumber(houseCreateDto.getHouseNumber());
        house.setAddress(houseCreateDto.getAddress());
        return house;
    }
}
