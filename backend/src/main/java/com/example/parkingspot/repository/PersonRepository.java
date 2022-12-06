package com.example.parkingspot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.parkingspot.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
  // Optional<Person> findPersonById(Long id);
  Optional<Person> findByFirstNameAndLastName(String firstname, String lastname);
}
