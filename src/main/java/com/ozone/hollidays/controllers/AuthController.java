package com.ozone.hollidays.controllers;

import com.ozone.hollidays.dtos.LoginRequest;
import com.ozone.hollidays.entities.Response;
import com.ozone.hollidays.entities.User;
import com.ozone.hollidays.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static java.time.LocalDateTime.now;

import org.springframework.web.bind.annotation.*;

import static java.util.Map.of;
import static org.springframework.http.HttpStatus.OK;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/signup")
    public ResponseEntity<Response> signup(@RequestBody User user) {
        authService.signup(user);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .message("User Registration success")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<Response> verifyAccount(@PathVariable String token) {
        authService.verifyAccount(token);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .message("Account Activated Successfully")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .message("User is authenticated")
                        .data(of("user", authService.login(loginRequest)))
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }


}
