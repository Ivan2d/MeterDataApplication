package com.phaselock.meter_data_application.repository;

import com.phaselock.meter_data_application.entity.Notification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query(value = """
                SELECT notification.* 
                FROM notification WHERE notification.type IN (:types) 
                AND notification.email IN (:emails);""",
            countQuery = """
                SELECT count(notification.*) 
                FROM notification WHERE notification.type IN (:types) 
                AND notification.email IN (:emails);""",
            nativeQuery = true)
    List<Notification> findAllByFilters(List<String> types, List<String> emails, Pageable pageable);
}