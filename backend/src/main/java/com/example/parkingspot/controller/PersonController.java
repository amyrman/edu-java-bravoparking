package com.example.parkingspot.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.parkingspot.entity.Person;
import com.example.parkingspot.service.PersonService;

@RestController
@RequestMapping("/api")
public class PersonController {
  private final PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping("/persons")
  public List<Person> getAllPersons() {
    return personService.getAllPersons();
  }

  @GetMapping("/persons/{id}")
  public ResponseEntity<Person> getPerson(@PathVariable(name = "id") Long personId) {
    Person foundPerson = personService.getPersonById(personId);
    if (foundPerson != null) {
      return ResponseEntity.ok().body(foundPerson);
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping("/persons")
  public ResponseEntity<Person> addNewPerson(@RequestBody Person person) {

    Person newUser = personService.registerNewPerson(person);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId())
        .toUri();

    if (newUser != null) {
      return ResponseEntity.created(location).body(newUser);
    }

    return ResponseEntity.internalServerError().build();
  }

}
