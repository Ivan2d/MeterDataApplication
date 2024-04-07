package com.phaselock.meter_data_application.repository;

import com.phaselock.meter_data_application.entity.ApartmentAndOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentAndOwnerRepository extends JpaRepository<ApartmentAndOwner, Integer> {
    Iterable<ApartmentAndOwner> findAllByApartmentId(Integer apartmentId);
    Iterable<ApartmentAndOwner> findAllByOwnerId(Integer ownerId);
}
