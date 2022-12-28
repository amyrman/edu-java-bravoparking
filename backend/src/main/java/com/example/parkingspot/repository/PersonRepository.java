package com.example.parkingspot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.parkingspot.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
  // Optional<Person> findPersonById(Long id);
  Optional<Person> findByFirstNameAndLastName(String firstname, String lastname);

  @Query("SELECT p FROM Person p WHERE p.userId = :userId")
  Optional<Person> findByUserId(@Param("userId") String userId);

  Person findByEmail(String email);

}
