package com.example.parkingspot.entity;

import java.util.List;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Zone {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Post new Zone passed as:
   * {
   * "name": "NAME OF ZONE",
   * "coordinate": {
   * "type": "point",
   * "coordinates": [
   * XX.XXXXXXX,
   * XX.XXXXXXX
   * ]
   * }
   * }
   */
  private Point<G2D> coordinate;
  private String name;
  @JsonIgnore
  @OneToMany(mappedBy = "zone", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  private List<Event> events;

  public Zone() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Event> getEvents() {
    return events;
  }

  public void setEvents(List<Event> events) {
    this.events = events;
  }

  public Point<G2D> getCoordinate() {
    return coordinate;
  }

  public void setCoordinate(Point<G2D> coordinate) {
    this.coordinate = coordinate;
  }

}
