package com.example.parkingspot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.parkingspot.service.UserService;

@RestController
@RequestMapping("/api/login")

public class UserController {

  private final UserService userService;


public UserController(UserService userService) {
  this.userService = userService;
  }

@GetMapping("/{email}")
  public String getUserId(@PathVariable ("email") String email) {
    return userService.getUserId(email);
  }

}
