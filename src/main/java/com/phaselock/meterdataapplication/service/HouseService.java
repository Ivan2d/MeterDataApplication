package com.phaselock.meterdataapplication.service;

import com.phaselock.meterdataapplication.dto.entity.HouseCreateDto;
import com.phaselock.meterdataapplication.entity.House;
import com.phaselock.meterdataapplication.exception.not_found_exception.NotFoundException;
import com.phaselock.meterdataapplication.mapper.entity.HouseMapper;
import com.phaselock.meterdataapplication.repository.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HouseService {
    private final HouseRepository houseRepository;
    private final HouseMapper houseMapper;

    public HouseCreateDto saveHouse(HouseCreateDto houseCreateDto) {
        return Optional.of(houseCreateDto)
                .map(houseMapper::map)
                .map(houseRepository::save)
                .map(houseMapper::map)
                .orElseThrow();
    }

    public Iterable<House> findAll() {
        return houseRepository.findAll();
    }

    public Optional<House> findById(Integer id) {
        return Optional.ofNullable(houseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("This house not exist")));
    }

    public void deleteById(Integer id) {
        houseRepository.findById(id)
                .ifPresent(account -> houseRepository.deleteById(id));
    }
}
