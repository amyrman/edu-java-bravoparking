package com.example.parkingspot.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.parkingspot.entity.Car;
import com.example.parkingspot.entity.Event;
import com.example.parkingspot.entity.Person;
import com.example.parkingspot.repository.CarRepository;
import com.example.parkingspot.service.CarService;
import com.example.parkingspot.service.EventService;
import com.example.parkingspot.service.PersonService;

import java.util.List;
import java.util.Optional;

@WebMvcTest(CarController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CarControllerMVCTest {
    
    @MockBean
    EventService eventService;

    @MockBean
    CarService carService;

    @MockBean
    CarRepository carRepository;

    @Autowired
    MockMvc mockMvc;


    @Test
    void callingEndpointGetCarsShouldReturn200OK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json("[]"));
    }


    @Test
    void getCarWithValidIdShould200OkAndCar() throws Exception {

        Car car = mockOneCar();
        Optional<Car> optionalCar = Optional.of(car);

        Mockito.when(carService.findCarById(ArgumentMatchers.any())).thenReturn(optionalCar);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars/{id}", 1L))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json("{\"id\":1, \"registration\":\"LMA714\"}"));
    }


    @Test
    void getCarWithNonexistingIdShouldReturnIsNotFound() throws Exception{

        Mockito.when(carService.findCarById(999L)).thenReturn(Optional.empty());

        mockMvc.perform( MockMvcRequestBuilders.get("/api/cars/{id}", 999L))
            .andExpect(MockMvcResultMatchers.status().isNotFound()); 
    }

    @Test
    void getCarWithIncorrectDatatypeShouldReturnBadRequest() throws Exception {

        Mockito.when(carService.findCarById(999L)).thenReturn(Optional.empty());

        RequestBuilder req = MockMvcRequestBuilders.get("/api/cars/{id}", "hej");

        mockMvc.perform(req).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    void addNewCarShouldReturn201CreatedAndCreatedCar() throws Exception {

        Car car = new Car();
        car.setId(99L);
        car.setRegistration("LMA714");

        Mockito.when(carService.addNewCar(ArgumentMatchers.any())).thenReturn(car);

        RequestBuilder request = MockMvcRequestBuilders
            .post("/api/cars")
            .accept(MediaType.APPLICATION_JSON)
            .content("{\"id\":99, \"registration\":\"LMA714\"}")
            .contentType(MediaType.APPLICATION_JSON);
        
            
        mockMvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json("{\"id\":99, \"registration\":\"LMA714\"}"));
        
    }

    @Test
    void getActiveParkingEventsShouldReturnListOfActiveParkingEvents() throws Exception {
        Event event = new Event();
        event.setId(1L);
        event.setActive(true);
        List<Event> events = List.of(event);

        Mockito.when(eventService.fetchEventsByIdAndActive(ArgumentMatchers.any())).thenReturn(events);

        RequestBuilder request = MockMvcRequestBuilders
            .get("/api/cars/{id}/events", 1L);

        mockMvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].active").value(true));

            
    }
 

    Car mockOneCar() {
        Car car = new Car();
        car.setId(1L);
        car.setRegistration("LMA714");
        return car;
    }
}
