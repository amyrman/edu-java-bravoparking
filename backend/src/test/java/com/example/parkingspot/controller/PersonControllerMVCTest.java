package com.example.parkingspot.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.parkingspot.entity.Person;
import com.example.parkingspot.service.PersonService;

@WebMvcTest(PersonController.class)
public class PersonControllerMVCTest {

  @MockBean
  private PersonService service;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void callingEndpointGetPersons_shouldReturnJsonAnd200OK() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/persons"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.content().json("[]"));
  }

  @Test
  void callingEndpointGetPerson_shouldReturnJsonAnd200OK() throws Exception {
    Person person = new Person();
    person.setFirstName("Test");
    person.setLastName("Efternamn");
    person.setId(1);

    when(service.getPersonById(1L)).thenReturn(person);

    mockMvc.perform(MockMvcRequestBuilders.get("/api/persons/{id}", 1L))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
  }

}
