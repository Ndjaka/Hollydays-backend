package com.ozone.hollidays.repositories;

import com.ozone.hollidays.entities.Holliday;
import com.ozone.hollidays.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface HollidayRepository extends JpaRepository<Holliday, Integer> {
     Optional<Holliday> findById(Integer id);
     Optional<Holliday> findByIdAndUser(Integer id, User currentUser);
}