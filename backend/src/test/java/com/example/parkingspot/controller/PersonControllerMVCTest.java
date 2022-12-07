package com.example.parkingspot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.parkingspot.service.PersonService;

@WebMvcTest(PersonController.class)
public class PersonControllerMVCTest {

  @MockBean
  private PersonService service;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void callingEndpointGetPersons_shouldReturn200OK() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/persons"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
  }

}
