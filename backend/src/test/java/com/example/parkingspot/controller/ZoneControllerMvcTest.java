package com.example.parkingspot.controller;

import java.util.List;

import org.assertj.core.util.Lists;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;
import static org.geolatte.geom.builder.DSL.*;
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

import com.example.parkingspot.entity.Zone;
import com.example.parkingspot.service.EventService;
import com.example.parkingspot.service.ZoneService;

@WebMvcTest(ZoneController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ZoneControllerMvcTest {

  @MockBean
  private ZoneService zoneService;

  @MockBean
  private EventService eventService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("Endpoint getZones - should return a list of zones in JSON")
  void callingGetZones_shoudlReturnJsonDataOfZones() throws Exception {
    List<Zone> dbZones = zonesSetup();

    Mockito.when(zoneService.getAllZones()).thenReturn(dbZones);

    RequestBuilder request = MockMvcRequestBuilders.get("/api/zones");

    mockMvc.perform(request)
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.content().json(
            "[{\"id\":2, \"name\":\"Parking location 2\"}, {\"id\":1, \"name\":\"Parking location 1\"}]"));
  }

  @Test
  @DisplayName("Endpoint addNewParkingZone - should return 201 Created and added Zone in JSON")
  void callingAddNewParkingZone_withValidData_shouldReturn201OkAndJsonData() throws Exception {
    Zone dbZone = mockOneZone();

    Mockito.when(zoneService.registerNewParkingZone(ArgumentMatchers.any())).thenReturn(dbZone);
    RequestBuilder request = MockMvcRequestBuilders.post("/api/zones").accept(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"Mocked zone\"}").contentType(MediaType.APPLICATION_JSON);

    mockMvc.perform(request)
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.content().json("{\"id\":1, \"name\":\"Mocked zone\"}"));
  }

  List<Zone> zonesSetup() {
    Zone z1 = new Zone();
    z1.setId(1L);
    z1.setName("Parking location 1");
    Point<G2D> pnt = point(WGS84, g(4.33, 53.21));
    // z1.setCoordinate(pnt);

    Zone z2 = new Zone();
    z2.setId(2L);
    z2.setName("Parking location 2");
    pnt = point(WGS84, g(14.33, 43.21));
    // z2.setCoordinate(pnt);

    List<Zone> zones = Lists.newArrayList(z1, z2);
    return zones;
  }

  Zone mockOneZone() {
    Zone zone = new Zone();
    zone.setId(1L);
    zone.setName("Mocked zone");
    return zone;
  }
}
