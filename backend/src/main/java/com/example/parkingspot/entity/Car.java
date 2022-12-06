package com.example.parkingspot.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Car {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true)
  private String registration;
  @ManyToOne
  private Person person;
  @JsonIgnore
  @OneToMany(mappedBy = "car", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  private List<Event> events;

  public Car() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRegistration() {
    return registration;
  }

  public void setRegistrationNr(String registrationNr) {
    this.registration = registrationNr;
  }

  public void setRegistration(String registration) {
    this.registration = registration;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

}
