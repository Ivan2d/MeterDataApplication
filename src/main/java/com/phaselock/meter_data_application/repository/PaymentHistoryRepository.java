package com.phaselock.meter_data_application.repository;

import com.phaselock.meter_data_application.entity.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Integer> {
}
