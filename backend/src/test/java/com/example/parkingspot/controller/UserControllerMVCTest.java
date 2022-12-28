package com.example.parkingspot.controller;

import com.example.parkingspot.repository.PersonRepository;
import com.example.parkingspot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerMVCTest {

    @MockBean
    PersonRepository personRepo;

    @MockBean
    UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void callingEndpointLogin_shouldReturn200OK_andId() throws Exception {

        when(userService.getUserId("dolores@test.nu")).thenReturn("1");

        mockMvc.perform(MockMvcRequestBuilders.get("/api/login/{email}", "dolores@test.nu"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }
}