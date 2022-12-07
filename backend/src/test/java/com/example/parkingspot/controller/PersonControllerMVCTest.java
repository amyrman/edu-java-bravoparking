package com.example.parkingspot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.parkingspot.service.PersonService;

@WebMvcTest(PersonController.class)
public class PersonControllerMVCTest {

  @MockBean
  private PersonService service;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void callingEndpointGetPersons_shouldReturn200OK() {

  }

}
