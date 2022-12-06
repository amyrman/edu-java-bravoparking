package com.example.parkingspot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.parkingspot.entity.Zone;

public interface ZoneRepository extends JpaRepository<Zone, Long> {

}
