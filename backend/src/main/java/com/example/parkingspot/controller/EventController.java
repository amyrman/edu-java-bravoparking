package com.example.parkingspot.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.parkingspot.entity.Event;
import com.example.parkingspot.service.EventService;

@RestController
@RequestMapping("/api/events")
public class EventController {

  private EventService eventService;

  public EventController(EventService eventService) {
    this.eventService = eventService;
  }

  // @GetMapping
  // public List<Event> getAllEvents() {
  // return eventService.fetchAllEvents();
  // }

  @GetMapping("/{id}")
  public ResponseEntity<Event> getEventById(@PathVariable("id") Long eventId) {
    Event foundEvent = eventService.fetchEventById(eventId);

    if (foundEvent != null) {
      return ResponseEntity.ok().body(foundEvent);
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping("/status")
  public ResponseEntity<List<Event>> getActiveEvents(@RequestParam("active") Boolean status) {
    List<Event> eventsList = eventService.fetchAllByActivityStatus(status);

    if (eventsList != null) {
      return ResponseEntity.ok().body(eventsList);
    }
    return ResponseEntity.notFound().build();
  }

  // To be moved or deleted
  @GetMapping("/car")
  public ResponseEntity<List<Event>> getEventsByRegistrationAndStatus(
      @RequestParam("registration") String carRegistration, @RequestParam("active") Boolean status) {
    List<Event> eventsList = eventService.fetchEventByRegistrationAndStatus(carRegistration, status);

    if (eventsList != null) {
      return ResponseEntity.ok().body(eventsList);
    }

    return ResponseEntity.notFound().build();
  }

  @GetMapping("/person")
  public ResponseEntity<List<Event>> getEventsByPersonIdAndStats(@RequestParam("id") String personId,
      @RequestParam("active") Boolean status) {
    List<Event> eventsList = eventService.fetchEventsByPersonIdAndStatus(personId, status);

    if (eventsList != null) {
      return ResponseEntity.ok().body(eventsList);
    }

    return ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<Event> addNewEvent(@RequestBody Event event) {
    Event newEvent = eventService.registerNewEvent(event);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newEvent.getId())
        .toUri();

    if (newEvent.getId() != null) {
      return ResponseEntity.created(location).body(newEvent);
    }

    return ResponseEntity.internalServerError().body(newEvent);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Event> updateEventStopTime(@PathVariable("id") Long eventId,
      @RequestParam("stopTime") LocalDateTime stopTime) {

    Event event = eventService.setNewEventStopTime(eventId, stopTime);

    if (event.getId() != null) {
      return ResponseEntity.ok().body(event);
    }

    return ResponseEntity.notFound().build();
  }
}
