package com.example.parkingspot.controller;

import java.util.List;

import org.assertj.core.util.Lists;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.parkingspot.entity.Zone;
import com.example.parkingspot.service.EventService;
import com.example.parkingspot.service.ZoneService;

import static org.assertj.core.api.Assertions.*;
import static org.geolatte.geom.builder.DSL.*;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

@ExtendWith(MockitoExtension.class)
public class ZoneControllerTest {

  @Mock
  EventService eventService;

  @Mock
  ZoneService zoneService;

  @InjectMocks
  ZoneController zoneController;

  @Test
  void getZones_shouldReturnListOfZones() {
    List<Zone> expected = zonesSetup();
    Mockito.when(zoneService.getAllZones()).thenReturn(expected);

    List<Zone> zones = zoneController.getAllZones();

    assertThat(zones).hasSize(2);
  }

  List<Zone> zonesSetup() {
    Zone z1 = new Zone();
    z1.setId(1L);
    z1.setName("Parking location 1");
    Point<G2D> pnt = point(WGS84, g(4.33, 53.21));
    z1.setCoordinate(pnt);

    Zone z2 = new Zone();
    z2.setId(2L);
    z2.setName("Parking location 2");
    pnt = point(WGS84, g(14.33, 43.21));
    z2.setCoordinate(pnt);

    List<Zone> zones = Lists.newArrayList(z1, z2);
    return zones;
  }

}