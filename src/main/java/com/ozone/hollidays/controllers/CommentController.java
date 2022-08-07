package com.ozone.hollidays.controllers;

import com.ozone.hollidays.dtos.CommentDto;
import com.ozone.hollidays.dtos.Response;
import com.ozone.hollidays.services.interfaces.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@SecurityRequirement(name = "Authorization" )
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(summary = "Holidays comments")
    @Tag(name = "comments")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the Comment",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CommentDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Comment not found", content = @Content)
    })
    @PostMapping("/add")
    public ResponseEntity<Response> add(@Parameter(description = "id of book to be searched") @RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(Response.builder()
                .status(HttpStatus.CREATED)
                .statusCode(OK.value())
                .message("Comments has been added")
                .data(Map.of("comments", commentService.add(commentDto)))
                .build());
    }
}
