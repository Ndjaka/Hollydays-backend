package com.ozone.hollidays.repositories;

import com.ozone.hollidays.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}