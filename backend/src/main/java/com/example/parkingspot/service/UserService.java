package com.example.parkingspot.service;

import org.springframework.stereotype.Service;

import com.example.parkingspot.entity.Person;
import com.example.parkingspot.repository.PersonRepository;

@Service
public class UserService {

private final PersonRepository personrepo;

  public UserService(PersonRepository personrepo) {
    this.personrepo = personrepo;
  }

  public String getUserId(String email) {
    Person foundUser = personrepo.findByEmail(email);
    return foundUser.getUserId();
  }
}
