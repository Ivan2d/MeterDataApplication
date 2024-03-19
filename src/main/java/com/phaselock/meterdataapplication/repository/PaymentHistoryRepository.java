package com.phaselock.meterdataapplication.repository;

import com.phaselock.meterdataapplication.entity.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Integer> {
}
