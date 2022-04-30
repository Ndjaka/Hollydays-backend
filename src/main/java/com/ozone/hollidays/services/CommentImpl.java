package com.ozone.hollidays.services;

import com.ozone.hollidays.dtos.CommentDto;
import com.ozone.hollidays.entities.Comment;
import com.ozone.hollidays.entities.Holliday;
import com.ozone.hollidays.exception.HollydaysException;
import com.ozone.hollidays.repositories.CommentRepository;
import com.ozone.hollidays.repositories.HollidayRepository;
import com.ozone.hollidays.services.interfaces.AuthService;
import com.ozone.hollidays.services.interfaces.CommentService;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class CommentImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AuthService authService;

    private final HollidayRepository HollidayRepository;

    public CommentImpl(CommentRepository commentRepository, AuthService authService, HollidayRepository HollidayRepository) {
        this.commentRepository = commentRepository;
        this.authService = authService;
        this.HollidayRepository = HollidayRepository;
    }

    @Override
    public CommentDto add(CommentDto commentDto) {
        Holliday holliday = HollidayRepository.getById(commentDto.getHoliday_id());
        if (holliday == null) new HollydaysException("Can not found Holyday");

        commentRepository.save(mapToComment(commentDto.getComment(), holliday));

        return commentDto;
    }

    private Comment mapToComment(String comment, Holliday holliday) {
        return Comment.builder()
                .user(authService.getCurrentUser())
                .hollidays(holliday)
                .message(comment)
                .date(Instant.now())
                .build();
    }
}
