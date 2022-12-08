package com.example.parkingspot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
  

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    
    return http
              .csrf().disable()
              .cors().disable()
              .formLogin().disable()
              .logout().disable()
              .sessionManagement().disable()

    .build();
  }
}
