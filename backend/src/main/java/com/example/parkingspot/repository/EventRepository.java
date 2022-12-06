package com.example.parkingspot.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.parkingspot.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
  // @Query("SELECT e FROM Event e WHERE e.active = :active")
  // List<Event> findByActiveStatus(@Param("active") Boolean bool);

  @Query("SELECT e FROM Event e WHERE e.active = :status")
  List<Event> findByActiveStatus(@Param("status") Boolean status);

  @Query("SELECT e FROM Event e WHERE e.car.registration = :registration AND e.active = :status")
  List<Event> findByRegistrationAndActive(@Param("registration") String registration, @Param("status") Boolean status);

  @Query("SELECT e FROM Event e WHERE e.car.person.id = :personId AND e.active = :status")
  List<Event> findByPersonIdAndStatus(@Param("personId") String personId, @Param("status") Boolean status);

  @Query("SELECT e FROM Event e WHERE e.zone.id = :zoneId AND e.start > :todaysDate")
  List<Event> findAllEventsByDate(@Param("zoneId") Long zoneId, @Param("todaysDate") LocalDateTime todaysDateString);

  @Query("SELECT e FROM Event e WHERE e.car.id = :id AND e.active = True")
  List<Event> findAllActiveEventsByCarId(@Param("id") Long id);

}
