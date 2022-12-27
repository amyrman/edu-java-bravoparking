package com.example.parkingspot.controller;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;

import com.example.parkingspot.entity.Car;
import com.example.parkingspot.service.CarService;


@ExtendWith(MockitoExtension.class)
public class CarControllerTest {
    
    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    @BeforeEach
    void configCarRepo() {

        Car car1 = new Car();
        car1.setId(1L);
        car1.setRegistration("LMA714");

        Car car2 = new Car();
        car2.setId(2L);
        car2.setRegistration("HPB274");

        Mockito.when(carController.getAllCars()).thenReturn(List.of(car1, car2));
    }


    @Test
    void getAllCarsShouldReturnCorrectSize() {

        assertThat(carController.getAllCars()).hasSize(2);
    }

}
