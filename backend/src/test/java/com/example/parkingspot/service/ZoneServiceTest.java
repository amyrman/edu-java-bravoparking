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

import java.util.Optional;

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

  @Test
  @DisplayName("Calling findZoneById - with valid id should return a Zone")
  void callingFindZoneById_withValidData_shouldReturnZone() {
    var dbZone = stubOneZone();
    long zoneId = 1l;
    Mockito.when(zoneRepository.findById(1l)).thenReturn(Optional.of(dbZone));

    Optional<Zone> zoneOptional = zoneRepository.findById(zoneId);
    var result = zoneOptional.get();

    assertThat(result).isInstanceOf(Zone.class);
    assertThat(result.getName()).isEqualTo("Stubbed Zone");
    assertThat(result.getId()).isEqualTo(zoneId);
    assertThat(result).hasFieldOrProperty("Events");
    assertThat(result).hasFieldOrProperty("Coordinate");

  }

  @Test
  @DisplayName("Calling findZoneById - with invalid id should return empty Optional")
  void callingFindZoneById_withInvalidData_shouldReturnZone() {
    // Mockito.when(zoneRepository.findById(99l)).thenReturn(Optional.empty());

    Optional<Zone> zoneOptional = zoneRepository.findById(1l);

    assertThat(zoneOptional).isInstanceOf(Optional.class);
    assertThat(zoneOptional.isEmpty()).isTrue();

  }

  Zone stubOneZone() {
    Zone zone = new Zone();
    zone.setId(1l);
    zone.setName("Stubbed Zone");
    return zone;
  }
}
