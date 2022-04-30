package com.ozone.hollidays.controllers;

import com.ozone.hollidays.services.userService.AuthServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
    @Autowired
    public MockMvc mockMvc;

    @Autowired
    AuthServiceImpl authService;
    @Test
    public void testSignUp(){
        Assertions.assertEquals(authService.getCurrentUser().getUserName() ,"NDJAKA EUGENE");
    }
}
