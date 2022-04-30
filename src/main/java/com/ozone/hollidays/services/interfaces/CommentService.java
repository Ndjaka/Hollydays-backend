package com.ozone.hollidays.services.interfaces;

import com.ozone.hollidays.dtos.CommentDto;
import com.ozone.hollidays.entities.Comment;

public interface CommentService {

    public CommentDto add(CommentDto commentDto);
}
