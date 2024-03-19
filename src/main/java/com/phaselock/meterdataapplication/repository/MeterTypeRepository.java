package com.phaselock.meterdataapplication.repository;

import com.phaselock.meterdataapplication.entity.MeterType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterTypeRepository extends JpaRepository<MeterType, Integer> {
}
