package com.ozone.hollidays.controllers;

import com.ozone.hollidays.dtos.LoginRequest;
import com.ozone.hollidays.dtos.RegisterRequest;
import com.ozone.hollidays.dtos.Response;
import com.ozone.hollidays.entities.User;
import com.ozone.hollidays.services.userService.AuthServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthServiceImpl authService;

    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }


    @PostMapping("/signup")
    @Operation(summary = "Signup user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the Comment",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Comment not found", content = @Content)
    })
    @Tag(name = "Authentication")
    public ResponseEntity<Response> signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
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
    @Operation(summary = "validate account")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the Comment",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "validate not found", content = @Content)
    })
    @Tag(name = "Authentication")
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
    @Operation(summary = "login user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the Comment",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Response.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "validate not found", content = @Content)
    })
    @Tag(name = "Authentication")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .message("user is connected")
                        .data(of("user", authService.login(loginRequest)))
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }


    @Operation(summary = "get connected user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Current user found",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Response.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "validate not found", content = @Content)
    })
    @Tag(name = "Authentication")
    @GetMapping("/get-connected-user")
    public User getConnectedUser() {
        return authService.getCurrentUser();
    }
}
