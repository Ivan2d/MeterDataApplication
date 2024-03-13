package com.phaselock.meterdataapplication.mapper.entity;

import com.phaselock.meterdataapplication.dto.entity.HouseCreateDto;
import com.phaselock.meterdataapplication.entity.House;
import org.springframework.stereotype.Component;

@Component
public class HouseMapper {
    public HouseCreateDto map(House house) {
        HouseCreateDto houseCreateDto = new HouseCreateDto();
        houseCreateDto.setHouseNumber(house.getHouseNumber());
        houseCreateDto.setAddress(house.getAddress());
        return houseCreateDto;
    }

    public House map(HouseCreateDto houseCreateDto) {
        House house = new House();
        house.setHouseNumber(houseCreateDto.getHouseNumber());
        house.setAddress(houseCreateDto.getAddress());
        return house;
    }
}
