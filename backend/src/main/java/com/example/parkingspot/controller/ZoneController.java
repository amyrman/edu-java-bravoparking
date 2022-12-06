package com.example.parkingspot.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.parkingspot.entity.Event;
import com.example.parkingspot.entity.Zone;
import com.example.parkingspot.service.EventService;
import com.example.parkingspot.service.ZoneService;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {
  private ZoneService zoneService;
  private EventService eventService;

  public ZoneController(ZoneService zoneService, EventService eventService) {
    this.zoneService = zoneService;
    this.eventService = eventService;
  }

  @GetMapping
  public List<Zone> getAllZones() {
    return zoneService.getAllZones();
  }

  @PostMapping
  public ResponseEntity<Zone> addNewParkingZone(@RequestBody Zone zone) {
    Zone newZone = zoneService.registerNewParkingZone(zone);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newZone.getId())
        .toUri();

    return ResponseEntity.created(location).body(newZone);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Zone> getZoneById(@PathVariable("id") Long zoneId) {
    Optional<Zone> zoneOptional = zoneService.findZoneById(zoneId);
    if (zoneOptional.isPresent()) {
      return ResponseEntity.ok().body(zoneOptional.get());
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping("/{id}/events/{date}")
  public List<Event> getAllEventsByDateAndZone(@PathVariable("id") Long zoneId, @PathVariable("date") LocalDate date) {
    return eventService.fetchAllEventsByDateAndZoneId(zoneId, date);
  }

}
