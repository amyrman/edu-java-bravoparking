package com.example.parkingspot.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

  // testa så att controllern returnerar String
  // hur testa det?
  // annan metod för decode alt i userservice
  // principal?

  @InjectMocks
  private UserController controller;

  // @Test
  // public void getUserIdShouldReturnString() {
   
  //   assertThat(controller.getUserId("dolores@test.nu")).isEqualTo("dolores@test.nu");
  // }

}
