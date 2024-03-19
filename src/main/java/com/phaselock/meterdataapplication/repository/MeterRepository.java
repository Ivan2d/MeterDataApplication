package com.phaselock.meterdataapplication.repository;

import com.phaselock.meterdataapplication.entity.Meter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeterRepository extends JpaRepository<Meter, Integer> {
    List<Meter> findAllByApartmentId(Integer apartment_id);
    @Query(nativeQuery = true, value =
                """
                SELECT meter.*
                FROM meter
                INNER JOIN apartment a ON meter.apartment_id = a.id
                INNER JOIN apartment_owner ao ON a.id = ao.apartment_id
                WHERE ao.owner_id = :ownerId
                """)
    List<Meter> findAllByApartmentAndOwnerId(@Param("ownerId") Integer id);
}
