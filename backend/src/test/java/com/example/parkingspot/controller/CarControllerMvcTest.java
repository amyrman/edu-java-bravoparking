package com.example.parkingspot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.parkingspot.service.CarService;
import com.example.parkingspot.service.EventService;

@WebMvcTest(CarController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CarControllerMvcTest {

  @MockBean
  private CarService carService;

  @MockBean
  private EventService eventService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void callingEndpointDeleteCar_shouldReturn200Ok() throws Exception {
    RequestBuilder request = MockMvcRequestBuilders.delete("/api/cars");

    mockMvc.perform(request)
        .andExpect(MockMvcResultMatchers.status().isOk());
  }
}
