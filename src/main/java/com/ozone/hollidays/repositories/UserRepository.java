package com.ozone.hollidays.repositories;

import com.ozone.hollidays.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String name);

    Optional<User> findByName(String username);
}