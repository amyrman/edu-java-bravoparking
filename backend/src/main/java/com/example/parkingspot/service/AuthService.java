package com.example.parkingspot.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  
  private EventService eventService;
  private UserService userService;

  public AuthService(EventService eventService, UserService userService) {
    this.eventService = eventService;
    this.userService = userService;
  }

  public void getAuth() {
  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
  String username = auth.getName();
  var userId = userService.getUserId(username);
  var foundEvent = eventService.fetchEventByUserId(userId);
  }
}
