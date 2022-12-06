package com.example.parkingspot.controller;

import java.net.URI;
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

import com.example.parkingspot.entity.Car;
import com.example.parkingspot.entity.Event;
import com.example.parkingspot.service.CarService;
import com.example.parkingspot.service.EventService;

@RestController
@RequestMapping("/api")
public class CarController {
  private final CarService carService;
  private final EventService eventService;

  public CarController(CarService carService, EventService eventService) {
    this.carService = carService;
    this.eventService = eventService;

  }

  @GetMapping("/cars")
  public List<Car> getAllCars() {
    return carService.getAllCars();
  }

  @GetMapping("/cars/{id}")
  public ResponseEntity<List<Car>> getCarsByOwner(@PathVariable("id") Long personId) {
    // List<Long> carpersonId = Arrays.asList(personId);
    List<Car> cars = carService.fetchCarsByOwnerId(personId);
    if (cars != null) {
      return ResponseEntity.ok().body(cars);
    }
    return ResponseEntity.notFound().build();

  }

  // @GetMapping("/car")
  // public Car getOwnersCar(@PathVariable("id") Long ownerId) {
  // return carService.getOneCarById(ownerId);
  // }

  @PostMapping("/cars")
  public ResponseEntity<Car> registerNewCar(@RequestBody Car car) {
    Car newCar = carService.addNewCar(car);
    // TODO: add checking/optional if write was successfull or not
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCar.getId())
        .toUri();
    return ResponseEntity.created(location).body(newCar);
  }

  @PutMapping("/cars/{id}")
  public ResponseEntity<Car> changeOwner(@PathVariable("id") Long carId,
      @RequestParam(required = true) Long newOwnerId) {
    Car foundCar = carService.updateCarOwner(carId, newOwnerId);
    if (foundCar != null) {
      return ResponseEntity.ok().body(foundCar);
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping("/cars/{id}/events")
  public ResponseEntity<List<Event>> getEventsByCarId(@PathVariable("id") Long id) {
    List<Event> eventsList = eventService.fetchEventsByIdAndActive(id);

    if (eventsList.size() > 0) {
      return ResponseEntity.ok().body(eventsList);
    }
    return ResponseEntity.notFound().build();
  }

}
