package com.phaselock.meterdataapplication.repository;

import com.phaselock.meterdataapplication.entity.ApartmentAndOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentAndOwnerRepository extends JpaRepository<ApartmentAndOwner, Integer> {
    Iterable<ApartmentAndOwner> findAllByApartmentId(Integer apartmentId);
    Iterable<ApartmentAndOwner> findAllByOwnerId(Integer ownerId);
}
