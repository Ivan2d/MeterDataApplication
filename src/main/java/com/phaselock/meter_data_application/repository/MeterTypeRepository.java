package com.phaselock.meter_data_application.repository;

import com.phaselock.meter_data_application.entity.MeterType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterTypeRepository extends JpaRepository<MeterType, Integer> {
}
