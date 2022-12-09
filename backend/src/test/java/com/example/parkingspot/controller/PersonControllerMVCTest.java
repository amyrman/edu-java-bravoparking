package com.example.parkingspot.controller;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
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
import com.example.parkingspot.entity.Person;
import com.example.parkingspot.service.CarService;
import com.example.parkingspot.service.PersonService;

@WebMvcTest(PersonController.class)
@AutoConfigureMockMvc(addFilters = false)
// disables security filters
public class PersonControllerMVCTest {

  @MockBean
  private PersonService personService;
  @MockBean
  private CarService carService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("Endpoint getPersons - should return 200 OK and Person array in JSON")
  void callingEndpointGetPersons_shouldReturnJsonAnd200OK() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/persons"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.content().json("[]"));
  }

  @Test
  @DisplayName("Endpoint getPersons/{id} - with valid id should return 200 OK and Person JSON")
  void callingEndpointGetPersons_withValidId_shouldReturnJsonAnd200OK() throws Exception {

    var person = mockOnePerson();

    Mockito.when(personService.getPersonById("410459b5-8c67-4d5f-a653-625726722ec3")).thenReturn(person);

    mockMvc.perform(MockMvcRequestBuilders.get("/api/persons/{id}", "410459b5-8c67-4d5f-a653-625726722ec3"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.content().json("{\"firstName\":\"Test\", \"lastName\":\"Efternamn\"}"));
  }

  @Test
  @DisplayName("Endpoint getPersons/{id} - with invalid id should return 404 Not Found")
  void callingEndpointGetPersons_withInvalidId_shouldReturn404NotFound() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/persons/{id}", 999L))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  @DisplayName("Endpoint addNewPerson - should return 201 Created and created Person JSON")
  void callingEndpointPostPersons_shouldReturn201CreatedAndJsonData() throws Exception {
    Person person = new Person();
    person.setFirstName("Test");
    person.setLastName("Efternamn");
    person.setUserId("410459b5-8c67-4d5f-a653-625726722ec3");

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
            MockMvcResultMatchers.content().json(
                "{\"firstName\":\"Test\",\"lastName\":\"Efternamn\", \"id\":1, \"userId\":\"410459b5-8c67-4d5f-a653-625726722ec3\"}"));
  }

  @Test
  @DisplayName("Endpoint getCarsByOwner - with valid userId should return 200 OK")
  void callingEndpointGetCarsByOwner_withValidId_ShouldReturn200Ok() throws Exception {

    Person person = mockOnePerson();
    Car car = new Car();
    car.setRegistration("ABC123");
    car.setPerson(person);
    car.setId(1L);

    List<Car> cars = List.of(car);

    Mockito.when(carService.fetchCarsByUserId("410459b5-8c67-4d5f-a653-625726722ec3")).thenReturn(cars);

    RequestBuilder request = MockMvcRequestBuilders
        .get("/api/persons/{id}/cars", "1").accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON);

    mockMvc.perform(request)
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  @DisplayName("Endpoint getCarsByOwner - with invalid userId should return 404 Not Found")
  void callingEndpointGetCarsByOwner_withInvalidId_ShouldReturn404NotFound() throws Exception {

    Mockito.when(carService.fetchCarsByUserId("wrongUserId")).thenReturn(null);

    RequestBuilder request = MockMvcRequestBuilders
        .get("/api/persons/{id}/cars", "wrongUserId");

    mockMvc.perform(request)
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  private Person mockOnePerson() {
    Person person = new Person();
    person.setFirstName("Test");
    person.setLastName("Efternamn");
    person.setId(1);
    person.setUserId("410459b5-8c67-4d5f-a653-625726722ec3");

    return person;
  }

}
