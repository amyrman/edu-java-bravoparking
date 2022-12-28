package com.example.parkingspot.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  
  private UserService userService;

  public AuthService(UserService userService) {
    this.userService = userService;
  }

  public String getAuth() {
  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
  String username = auth.getName();
  return userService.getUserId(username);
  }
}
