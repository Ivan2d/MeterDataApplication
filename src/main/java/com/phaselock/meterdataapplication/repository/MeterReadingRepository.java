package com.phaselock.meterdataapplication.repository;

import com.phaselock.meterdataapplication.entity.MeterReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterReadingRepository extends JpaRepository<MeterReading, Integer> {
}
