package com.ozone.hollidays.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ozone.hollidays.dtos.Response;
import com.ozone.hollidays.entities.Holliday;
import com.ozone.hollidays.repositories.HollidayRepository;
import com.ozone.hollidays.repositories.ImageRepository;
import com.ozone.hollidays.services.HolidayServiceImpl;
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
public class HolidayController {

    private final HolidayServiceImpl holidayService;

    public HolidayController(HolidayServiceImpl holidayService) {
        this.holidayService = holidayService;
    }

    @PostMapping("/save")
    public ResponseEntity<Response> saveHollidays(@RequestBody Holliday holliday){
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

    @PostMapping("/save-images-of-holiday/{id}")
    public ResponseEntity<Response> saveImageOfHoliday(@PathVariable("id") Integer id,@RequestParam("files")  List<MultipartFile> multipartFiles){
        System.out.println("images "+id);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .message("images is save ")
                        .data(Map.of("images",holidayService.saveImagesOfHoliday(id,multipartFiles)))
                        .status(CREATED)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getHolidays(@RequestParam("page") int page, @RequestParam("per_page") int per_page){
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
