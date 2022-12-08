package com.example.parkingspot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.parkingspot.entity.Person;
import com.example.parkingspot.repository.PersonRepository;

@Service
public class PersonService {
  private final PersonRepository personRepository;

  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public Person getPersonById(Long personId) {
    Optional<Person> personOptional = personRepository.findById(personId);
    if (personOptional.isPresent()) {
      return personOptional.get();
    }
    return null;
  }

  public List<Person> getAllPersons() {
    return personRepository.findAll();
  }

  public Person registerNewPerson(Person person) {
    // . EDIT THIS CHECK FOR OTHER UNIQUE PROP -
    // Optional<Person> existingPerson =
    // personRepository.findByFirstNameAndLastName(person.getFirstName(),
    // person.getLastName());
    // if (existingPerson.isPresent()) {
    // return person;
    // }

    return personRepository.save(person);
  }

}
