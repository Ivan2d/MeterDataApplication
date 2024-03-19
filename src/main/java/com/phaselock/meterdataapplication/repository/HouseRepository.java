package com.phaselock.meterdataapplication.repository;

import com.phaselock.meterdataapplication.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House, Integer> {
}
