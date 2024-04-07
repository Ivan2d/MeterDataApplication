package com.phaselock.meter_data_application.service;

import com.phaselock.meter_data_application.dto.entity.create.ApartmentCreateDto;
import com.phaselock.meter_data_application.dto.entity.read.ApartmentReadDto;
import com.phaselock.meter_data_application.dto.entity.read.OwnerReadDto;
import com.phaselock.meter_data_application.entity.Apartment;
import com.phaselock.meter_data_application.entity.ApartmentAndOwner;
import com.phaselock.meter_data_application.entity.Owner;
import com.phaselock.meter_data_application.exception.NotFoundException;
import com.phaselock.meter_data_application.mapper.entity.ApartmentMapper;
import com.phaselock.meter_data_application.mapper.entity.OwnerMapper;
import com.phaselock.meter_data_application.repository.ApartmentAndOwnerRepository;
import com.phaselock.meter_data_application.repository.ApartmentRepository;
import com.phaselock.meter_data_application.repository.OwnerRepository;
import com.phaselock.meter_data_application.util.DataByJwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ApartmentAndOwnerService {
    private final ApartmentAndOwnerRepository apartmentAndOwnerRepository;
    private final ApartmentRepository apartmentRepository;
    private final OwnerRepository ownerRepository;
    private final ApartmentMapper apartmentMapper;
    private final OwnerMapper ownerMapper;


    public ApartmentReadDto addApartment(ApartmentCreateDto apartmentCreateDto, Jwt jwt) {
        String username = DataByJwtUtil.getUsernameJwtClaim(jwt);
        Apartment apartment = apartmentRepository.save(apartmentMapper.map(apartmentCreateDto));
        Owner owner = findOwnerByFirstname(username);
        ApartmentAndOwner apartmentAndOwner = new ApartmentAndOwner();
        apartmentAndOwner.setOwner(owner);
        apartmentAndOwner.setApartment(apartment);
        apartmentAndOwnerRepository.save(apartmentAndOwner);
        return apartmentMapper.map(apartment);
    }

    public Optional<List<OwnerReadDto>> findAllOwnersByApartmentId(Integer apartmentId) {
        return Optional.of(apartmentRepository.findById(apartmentId)
                .map(apartment -> {
                    List<Owner> owners = new ArrayList<>();
                    for (ApartmentAndOwner apartmentAndOwner : apartmentAndOwnerRepository.findAllByApartmentId(apartment.getId())) {
                        owners.add(apartmentAndOwner.getOwner());
                    }
                    return owners.stream()
                            .map(ownerMapper::map)
                            .collect(Collectors.toList());
                })
                .orElseThrow(() -> new NotFoundException("This apartment not exist")));
    }

    public Optional<List<ApartmentReadDto>> findAllApartmentsByOwnerId(Integer ownerId) {
        return Optional.of(ownerRepository.findById(ownerId)
                .map(apartment -> {
                    List<Apartment> owners = new ArrayList<>();
                    for (ApartmentAndOwner apartmentAndOwner : apartmentAndOwnerRepository.findAllByOwnerId(apartment.getId())) {
                        owners.add(apartmentAndOwner.getApartment());
                    }
                    return owners.stream()
                            .map(apartmentMapper::map)
                            .collect(Collectors.toList());
                })
                .orElseThrow(() -> new NotFoundException("This owner not exist")));
    }

    public void deleteById(Integer id) {
        apartmentAndOwnerRepository.findById(id)
                .ifPresent(account -> apartmentAndOwnerRepository.deleteById(id));
    }

    private Owner findOwnerByFirstname(String firstname) {
        return ownerRepository.findByFirstName(firstname).orElseThrow(
                () -> new NotFoundException("This user not found"));
    }
}
