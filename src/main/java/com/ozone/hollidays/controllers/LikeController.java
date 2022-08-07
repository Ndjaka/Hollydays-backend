package com.ozone.hollidays.controllers;

import com.ozone.hollidays.dtos.LikeDto;
import com.ozone.hollidays.services.LikeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/v1/like")
public class LikeController {

    private final LikeServiceImpl likeService;

    public LikeController(LikeServiceImpl likeService) {
        this.likeService = likeService;
    }


    @PostMapping("/save")
    ResponseEntity<?> addLikeOrDislike(@RequestBody LikeDto likeDto){
      return ResponseEntity.ok(likeService.addLikeOrRemoveLike(likeDto));
    }

}
