package com.example.parkingspot.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.parkingspot.entity.Person;
import com.example.parkingspot.repository.PersonRepository;

@Service
public class PersonService {
  private final PersonRepository personRepository;

  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public Person getPersonById(String userId) {
    // Optional<Person> personOptional = personRepository.findById(userId);
    Optional<Person> personOptional = personRepository.findByUserId(userId);
    if (personOptional.isPresent()) {
      return personOptional.get();
    }
    return null;
  }

  public List<Person> getAllPersons() {
    return personRepository.findAll();
  }

  public Person registerNewPerson(Person person) {
    Optional<Person> existingPerson = personRepository.findByFirstNameAndLastName(person.getFirstName(),
        person.getLastName());
    if (existingPerson.isPresent()) {
      return person;
    }

    person.setUserId(UUID.randomUUID().toString());

    Person newPerson = personRepository.save(person);
    if (newPerson.getId() > 0) {
      return newPerson;
    }
    return null;
  }

}
