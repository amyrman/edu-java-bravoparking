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

import com.example.parkingspot.entity.Person;
import com.example.parkingspot.service.PersonService;

@WebMvcTest(PersonController.class)
@AutoConfigureMockMvc(addFilters = false)
// disables security filters
public class PersonControllerMVCTest {

  @MockBean
  private PersonService personService;

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
  void callingEndpointGetPersons_withValidId_shouldReturnJsonAnd200OK() throws Exception {

    var person = mockOnePerson();

    Mockito.when(personService.getPersonById(1L)).thenReturn(person);

    mockMvc.perform(MockMvcRequestBuilders.get("/api/persons/{id}", 1L))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.content().json("{\"firstName\":\"Test\", \"lastName\":\"Efternamn\"}"));
  }

  @Test
  void callingEndpointGetPersons_withInvalidId_shouldReturn404NotFound() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/persons/{id}", 999L))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  void callingEndpointPostPersons_shouldReturn201CreatedAndJsonData() throws Exception {
    Person person = new Person();
    person.setFirstName("Test");
    person.setLastName("Efternamn");

    Person expectedPerson = mockOnePerson();

    Mockito.when(personService.registerNewPerson(ArgumentMatchers.any())).thenReturn(expectedPerson);

    RequestBuilder request = MockMvcRequestBuilders.post("/api/persons").accept(MediaType.APPLICATION_JSON)
        .content("{\"firstName\":\"Test\",\"lastName\":\"Efternamn\"}")
        .contentType(MediaType.APPLICATION_JSON);

    mockMvc
        .perform(request)
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(
            MockMvcResultMatchers.content().json("{\"firstName\":\"Test\",\"lastName\":\"Efternamn\", \"id\":1}"));
  }

  private Person mockOnePerson() {
    Person person = new Person();
    person.setFirstName("Test");
    person.setLastName("Efternamn");
    person.setId(1);

    return person;
  }

}
