package com.example.parkingspot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.parkingspot.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

  @Query("SELECT c FROM Car c WHERE c.person.id = :personId")
  List<Car> getAllCarsByPersonId(@Param("personId") Long personId);

  Optional<Car> findByRegistration(String registration);

}
