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

import com.example.parkingspot.entity.Person;
import com.example.parkingspot.service.PersonService;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {

  @Mock
  PersonService service;

  @InjectMocks
  PersonController controller;

  @BeforeEach
  void configurePersonService() {
    Person p1 = new Person();
    p1.setFirstName("Test");
    p1.setLastName("Testson");
    p1.setId(1);
    Person p2 = new Person();
    p2.setFirstName("Another");
    p2.setLastName("One");
    p2.setId(2);
    Mockito.when(service.getAllPersons()).thenReturn(List.of(p1, p2));
  }

  @Test
  void getAllPersons_shouldReturnListOfPersons() {
    List<Person> result = controller.getAllPersons();

    assertThat(result).hasSize(2);
  }
}
