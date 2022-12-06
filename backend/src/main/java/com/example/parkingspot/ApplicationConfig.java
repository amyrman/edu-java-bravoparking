package com.example.parkingspot;

import org.geolatte.geom.json.GeolatteGeomModule;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.parkingspot.entity.Car;
import com.example.parkingspot.entity.Event;
import com.example.parkingspot.entity.Person;
import com.example.parkingspot.entity.Zone;
import com.example.parkingspot.repository.CarRepository;
import com.example.parkingspot.repository.EventRepository;
import com.example.parkingspot.repository.PersonRepository;
import com.example.parkingspot.repository.ZoneRepository;

import static org.geolatte.geom.builder.DSL.*;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

import java.time.LocalDateTime;

import org.geolatte.geom.*;

@Configuration
public class ApplicationConfig {

  @Bean
  GeolatteGeomModule geomModule() {
    return new GeolatteGeomModule();
  }

  @Bean
  CommandLineRunner init(PersonRepository personRepository, CarRepository carRepository, ZoneRepository zoneRepository,
      EventRepository eventRepository) {
    return args -> {
      // DB INSERTS BELOW

      // Person inserts
      Person p1 = new Person();
      p1.setFirstName("Jessica");
      p1.setLastName("Jones");
      personRepository.save(p1);

      Person p2 = new Person();
      p2.setFirstName("Harley");
      p2.setLastName("Quinn");
      personRepository.save(p2);

      Person p3 = new Person();
      p3.setFirstName("James");
      p3.setLastName("West");
      personRepository.save(p3);

      Person p4 = new Person();
      p4.setFirstName("Keanu");
      p4.setLastName("Reaves");
      personRepository.save(p4);

      Person p5 = new Person();
      p5.setFirstName("Gwen");
      p5.setLastName("Stacey");
      personRepository.save(p5);

      Person p6 = new Person();
      p6.setFirstName("Kamala");
      p6.setLastName("Khan");
      personRepository.save(p6);

      Person p7 = new Person();
      p7.setFirstName("Neo");
      p7.setLastName("Andersson");
      personRepository.save(p7);

      Person p8 = new Person();
      p8.setFirstName("Tony");
      p8.setLastName("Stark");
      personRepository.save(p8);

      // Car inserts
      Car c1 = new Car();
      c1.setRegistration("JEJ001");
      c1.setPerson(p1);
      carRepository.save(c1);

      Car c2 = new Car();
      c2.setRegistration("HAQ232");
      c2.setPerson(p2);
      carRepository.save(c2);

      Car c3 = new Car();
      c3.setRegistration("JAW556");
      c3.setPerson(p3);
      carRepository.save(c3);

      Car c4 = new Car();
      c4.setRegistration("KER110");
      c4.setPerson(p4);
      carRepository.save(c4);

      Car c5 = new Car();
      c5.setRegistration("GWS979");
      c5.setPerson(p5);
      carRepository.save(c5);

      Car c6 = new Car();
      c6.setRegistration("KAH371");
      c6.setPerson(p6);
      carRepository.save(c6);

      Car c7 = new Car();
      c7.setRegistration("TOS612");
      c7.setPerson(p8);
      carRepository.save(c7);

      // Zone inserts
      Zone z1 = new Zone();
      z1.setName("Jarls Parkeringar");
      Point<G2D> pnt = point(WGS84, g(4.33, 53.21));
      z1.setCoordinate(pnt);
      zoneRepository.save(z1);

      Zone z2 = new Zone();
      z2.setName("Smurfit Gatuparkeringar");
      pnt = point(WGS84, g(-10, 52));
      z2.setCoordinate(pnt);
      zoneRepository.save(z2);

      Zone z3 = new Zone();
      z3.setName("Park Here AB");
      pnt = point(WGS84, g(10, 32));
      z3.setCoordinate(pnt);
      zoneRepository.save(z3);

      Zone z4 = new Zone();
      z4.setName("Gatuparkering Zon1");
      pnt = point(WGS84, g(43, -8));
      z4.setCoordinate(pnt);
      zoneRepository.save(z4);

      // Event inserts
      Event e1 = new Event();
      e1.setZone(z1);
      e1.setCar(c6);
      e1.setStart(LocalDateTime.parse("2022-11-22T12:00:00"));
      e1.setStop(LocalDateTime.parse("2022-11-22T15:00:00"));
      e1.setActive(false);
      eventRepository.save(e1);

      Event e2 = new Event();
      e2.setZone(z1);
      e2.setCar(c1);
      e2.setStart(LocalDateTime.parse("2022-11-21T15:00:00"));
      e2.setStop(LocalDateTime.parse("2022-11-21T16:00:00"));
      e2.setActive(false);
      eventRepository.save(e2);

      Event e3 = new Event();
      e3.setZone(z2);
      e3.setCar(c2);
      e3.setStart(LocalDateTime.now());
      e3.setStop(LocalDateTime.now().plusHours(2).plusMinutes(20));
      eventRepository.save(e3);

      Event e4 = new Event();
      e4.setZone(z3);
      e4.setCar(c4);
      e4.setStart(LocalDateTime.now());
      e4.setStop(LocalDateTime.now().plusHours(1).plusMinutes(25));
      eventRepository.save(e4);

      Event e5 = new Event();
      e5.setZone(z1);
      e5.setCar(c3);
      e5.setStart(LocalDateTime.now());
      e5.setStop(LocalDateTime.now().plusHours(1).plusMinutes(25));
      eventRepository.save(e5);

      Event e6 = new Event();
      e6.setZone(z3);
      e6.setCar(c5);
      e6.setStart(LocalDateTime.parse("2022-11-21T09:00:00"));
      e6.setStop(LocalDateTime.parse("2022-11-21T12:21:00"));
      e6.setActive(false);
      eventRepository.save(e6);

      Event e7 = new Event();
      e7.setZone(z1);
      e7.setCar(c2);
      e7.setStart(LocalDateTime.parse("2022-11-20T07:30:00"));
      e7.setStop(LocalDateTime.parse("2022-11-20T08:21:00"));
      e7.setActive(false);
      eventRepository.save(e7);

      Event e8 = new Event();
      e8.setZone(z1);
      e8.setCar(c2);
      e8.setStart(LocalDateTime.parse("2022-11-21T07:30:00"));
      e8.setStop(LocalDateTime.parse("2022-11-21T08:21:00"));
      e8.setActive(false);
      eventRepository.save(e8);

      Event e9 = new Event();
      e9.setZone(z1);
      e9.setCar(c1);
      e9.setStart(LocalDateTime.parse("2022-11-21T10:22:00"));
      e9.setStop(LocalDateTime.parse("2022-11-21T14:41:00"));
      e9.setActive(false);
      eventRepository.save(e9);

      Event e10 = new Event();
      e10.setZone(z1);
      e10.setCar(c1);
      e10.setStart(LocalDateTime.parse("2022-11-22T11:22:00"));
      e10.setStop(LocalDateTime.parse("2022-11-22T12:11:00"));
      e10.setActive(false);
      eventRepository.save(e10);

      Event e11 = new Event();
      e11.setZone(z3);
      e11.setCar(c6);
      e11.setStart(LocalDateTime.now());
      e11.setStop(LocalDateTime.now().plusHours(3).plusMinutes(13));
      eventRepository.save(e11);

      Event e12 = new Event();
      e12.setZone(z3);
      e12.setCar(c5);
      e12.setStart(LocalDateTime.now());
      e12.setStop(LocalDateTime.now().plusHours(3).plusMinutes(13));
      eventRepository.save(e12);

      Event e13 = new Event();
      e13.setZone(z4);
      e13.setCar(c7);
      e13.setStart(LocalDateTime.now());
      e13.setStop(LocalDateTime.now().plusHours(1).plusMinutes(15));
      eventRepository.save(e13);
    };
  }
}
