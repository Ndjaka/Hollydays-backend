package com.ozone.hollidays.repositories;

import com.ozone.hollidays.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByHollidays_Id(int holiday_id);

}

