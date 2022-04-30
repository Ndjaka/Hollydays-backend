package com.ozone.hollidays.repositories;

import com.ozone.hollidays.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Integer> {
}