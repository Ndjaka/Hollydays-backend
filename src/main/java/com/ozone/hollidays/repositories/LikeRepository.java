package com.ozone.hollidays.repositories;

import com.ozone.hollidays.entities.Holliday;
import com.ozone.hollidays.entities.Like;
import com.ozone.hollidays.entities.User;
import com.ozone.hollidays.enums.LikesType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Integer> {
    @Override
    Optional<Like> findById(Integer integer);

    Optional<Like> findTopByHollidayAndUserOrderByIdDesc(Holliday holliday, User currentUser);

    List<Like> findLikesByHollidayAndLikeType(Holliday holliday, LikesType type);
}