package com.phaselock.meter_data_application.repository;

import com.phaselock.meter_data_application.entity.MeterReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterReadingRepository extends JpaRepository<MeterReading, Integer> {
}
