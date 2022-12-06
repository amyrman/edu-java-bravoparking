package com.example.parkingspot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.parkingspot.entity.Car;
import com.example.parkingspot.entity.Person;
import com.example.parkingspot.repository.CarRepository;

import jakarta.transaction.Transactional;

@Service
public class CarService {
  private final CarRepository carRepository;
  private final PersonService personService;

  public CarService(CarRepository carRepository, PersonService personService) {
    this.carRepository = carRepository;
    this.personService = personService;

  }

  public Car addNewCar(Car car) {
    Person owner = personService.getPersonById(car.getPerson().getId());
    car.setPerson(owner);
    return carRepository.save(car);
  }

  public List<Car> fetchCarsByOwnerId(Long personId) {
    return carRepository.getAllCarsByPersonId(personId);
  }

  public Car getOneCarByPersonId(Long personId) {
    return carRepository.findById(personId).orElseThrow(() -> new RuntimeException("No car found"));
  }

  public Optional<Car> findCarById(Long carId) {
    return carRepository.findById(carId);
  }

  public Optional<Car> fetchCarByRegistration(String registration) {
    return carRepository.findByRegistration(registration);
  }

  public List<Car> getAllCars() {
    return carRepository.findAll();
  }

  @Transactional
  public Car updateCarOwner(Long carId, Long newOwnerId) {
    Optional<Car> carOptional = carRepository.findById(carId);
    Person newOwner = personService.getPersonById(newOwnerId);
    if (carOptional.isPresent() && newOwner != null) {
      Car foundCar = carOptional.get();
      foundCar.setPerson(newOwner);
      return foundCar;
    }
    return null;
  }

}
