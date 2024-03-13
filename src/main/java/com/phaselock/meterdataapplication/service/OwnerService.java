package com.phaselock.meterdataapplication.service;

import com.phaselock.meterdataapplication.dto.entity.OwnerCreateDto;
import com.phaselock.meterdataapplication.entity.Owner;
import com.phaselock.meterdataapplication.exception.not_found_exception.NotFoundException;
import com.phaselock.meterdataapplication.mapper.entity.OwnerMapper;
import com.phaselock.meterdataapplication.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    public OwnerCreateDto saveOwner(OwnerCreateDto ownerCreateDto) {
        return Optional.of(ownerCreateDto)
                .map(ownerMapper::map)
                .map(ownerRepository::save)
                .map(ownerMapper::map)
                .orElseThrow();
    }

    public Iterable<Owner> findAll() {
        return ownerRepository.findAll();
    }

    public Optional<Owner> findById(Integer id) {
        return Optional.ofNullable(ownerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("This owner not exist")));
    }

    public void deleteById(Integer id) {
        ownerRepository.findById(id)
                .ifPresent(account -> ownerRepository.deleteById(id));
    }
}
