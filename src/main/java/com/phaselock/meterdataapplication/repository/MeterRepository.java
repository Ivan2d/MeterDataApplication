package com.phaselock.meterdataapplication.repository;

import com.phaselock.meterdataapplication.entity.Meter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterRepository extends JpaRepository<Meter, Integer> {
    Iterable<Meter> findAllByApartmentId(Integer apartment_id);
    @Query(nativeQuery = true, value =
                """
                SELECT meter.*
                FROM meter INNER JOIN apartment a ON meter.apartment_id = a.id
                INNER JOIN owner o ON a.id = o.apartment_id WHERE o.id = :ownerId
                """)
    Iterable<Meter> findAllByApartmentAndOwnerId(@Param("ownerId") Integer id);
}
