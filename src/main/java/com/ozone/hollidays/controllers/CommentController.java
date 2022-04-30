package com.ozone.hollidays.controllers;

import com.ozone.hollidays.dtos.CommentDto;
import com.ozone.hollidays.dtos.Response;
import com.ozone.hollidays.services.interfaces.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add")
    public ResponseEntity<Response> add(@RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(Response.builder()
                .status(HttpStatus.CREATED)
                .statusCode(OK.value())
                .message("Comments has been added")
                .data(Map.of("comments", commentService.add(commentDto)))
                .build());
    }
}
