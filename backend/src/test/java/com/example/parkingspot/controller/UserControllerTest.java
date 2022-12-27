package com.example.parkingspot.controller;

import com.example.parkingspot.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

  @Mock
  UserService service;

  @InjectMocks
  private UserController controller;

  @Test
  void getUserId_shouldReturnString() {
  when(service.getUserId("dolores@test.nu")).thenReturn("abc");

  assertThat(controller.getUserId("dolores@test.nu")).isEqualTo("abc");
  }

}
