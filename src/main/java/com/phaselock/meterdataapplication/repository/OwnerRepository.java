package com.phaselock.meterdataapplication.repository;

import com.phaselock.meterdataapplication.entity.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Integer> {
}
