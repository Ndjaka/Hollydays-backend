package com.ozone.hollidays.controllers;

import com.ozone.hollidays.dtos.Response;
import com.ozone.hollidays.entities.Holliday;
import com.ozone.hollidays.services.HolidayServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/holiday")
@SecurityRequirement(name = "Authorization")
public class HolidayController {

    private final HolidayServiceImpl holidayService;

    public HolidayController(HolidayServiceImpl holidayService) {
        this.holidayService = holidayService;
    }

    @PostMapping("/save")
    @Operation(summary = "save user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "the user has been successfully registered",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Response.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid is passed", content = @Content),
            @ApiResponse(responseCode = "404", description = "validate not found", content = @Content)
    })
    @Tag(name = "Holiday")
    public ResponseEntity<Response> saveHoliday(
           @RequestBody Holliday holliday){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .message("Holiday is save ")
                        .data(Map.of("hollidays",holidayService.saveHoliday(holliday)))
                        .status(CREATED)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PutMapping ("/update")
    @Operation(summary = "update user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "the user has been successfully registered",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Response.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid is passed", content = @Content),
            @ApiResponse(responseCode = "404", description = "validate not found", content = @Content)
    })
    @Tag(name = "Holiday")
    public ResponseEntity<Response> updateHoliday(
            @RequestBody Holliday holliday){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .message("Holiday is save ")
                        .data(Map.of("hollidays",holidayService.updaTeHoliday(holliday)))
                        .status(CREATED)
                        .statusCode(OK.value())
                        .build()
        );
    }


    @PostMapping("/save-images/{id}")
    @Operation(summary = "add image on holiday")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "image has been successfully registered",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Response.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id is passed", content = @Content),
            @ApiResponse(responseCode = "404", description = "validate not found", content = @Content)
    })
    @Tag(name = "Holiday")
    public ResponseEntity<Response> saveImageOfHoliday(
            @PathVariable("id") Integer id,
            @RequestParam("files")  List<MultipartFile> multipartFiles){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .message("images is save")
                        .data(Map.of("images",holidayService.saveImagesOfHoliday(id,multipartFiles)))
                        .status(CREATED)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/all")
    @Operation(summary = "save holiday")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Holiday has been successfully registered",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Response.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid parameter is passed", content = @Content),
            @ApiResponse(responseCode = "404", description = "validate not found", content = @Content)
    })
    @Tag(name = "Holiday")
    public ResponseEntity<Response> getHolidays(
            @RequestParam("page") int page,
            @RequestParam("per_page") int per_page) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .message("list of Holidays ")
                        .data(Map.of("Holidays",holidayService.getHolidays(page, per_page)))
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
  }
