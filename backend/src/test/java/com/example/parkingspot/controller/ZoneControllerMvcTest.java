package com.example.parkingspot.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
  @DisplayName("Endpoint getZones - should return a list of zones")
  void callingGetZones_shoudlReturnJsonDataOfZones() throws Exception {

    Mockito.when(zoneService.getAllZones()).thenReturn(null);

    RequestBuilder request = MockMvcRequestBuilders.get("/api/zones");

    mockMvc.perform(request).andExpect(null);
  }
}
