package com.phaselock.meterdataapplication.service;

import com.phaselock.meterdataapplication.dto.entity.create.HouseCreateDto;
import com.phaselock.meterdataapplication.dto.entity.read.HouseReadDto;
import com.phaselock.meterdataapplication.exception.NotFoundException;
import com.phaselock.meterdataapplication.mapper.entity.HouseMapper;
import com.phaselock.meterdataapplication.repository.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HouseService {
    private final HouseRepository houseRepository;
    private final HouseMapper houseMapper;

    public HouseReadDto saveHouse(HouseCreateDto houseCreateDto) {
        return Optional.of(houseCreateDto)
                .map(houseMapper::map)
                .map(houseRepository::save)
                .map(houseMapper::map)
                .orElseThrow();
    }

    public List<HouseReadDto> findAll() {
        return houseRepository.findAll()
                .stream()
                .map(houseMapper::map)
                .collect(Collectors.toList());
    }

    public Optional<HouseReadDto> findById(Integer id) {
        return Optional.ofNullable(houseRepository.findById(id)
                .map(houseMapper::map)
                .orElseThrow(() -> new NotFoundException("This house not exist")));
    }

    public void deleteById(Integer id) {
        houseRepository.findById(id)
                .ifPresent(account -> houseRepository.deleteById(id));
    }
}
