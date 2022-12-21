package com.example.parkingspot.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.parkingspot.entity.Zone;
import com.example.parkingspot.repository.ZoneRepository;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ZoneServiceTest {
  @Mock
  ZoneRepository zoneRepository;

  @InjectMocks
  ZoneService zoneService;

  @Test
  @DisplayName("Calling registerNewParkingZone - should return saved Zone")
  void callingRegisterNewParkingZone_shouldReturnSavedZone() {
    Zone newZone = new Zone();
    newZone.setName("Stubbed Zone");
    var dbZone = stubOneZone();

    Mockito.when(zoneRepository.save(newZone)).thenReturn(dbZone);
    Zone result = zoneService.registerNewParkingZone(newZone);

    assertThat(result.getId()).isEqualByComparingTo(1l);
    assertThat(result.getName()).isEqualTo("Stubbed Zone");
    assertThat(result).hasFieldOrProperty("Events");
    assertThat(result).hasFieldOrProperty("Coordinate");
    assertThat(result).isInstanceOf(Zone.class);

  }

  Zone stubOneZone() {
    Zone zone = new Zone();
    zone.setId(1l);
    zone.setName("Stubbed Zone");
    return zone;
  }
}
