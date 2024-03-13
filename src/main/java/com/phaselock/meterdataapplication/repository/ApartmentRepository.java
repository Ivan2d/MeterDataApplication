package com.phaselock.meterdataapplication.repository;

import com.phaselock.meterdataapplication.entity.Apartment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository extends CrudRepository<Apartment, Integer> {
}
