package com.ozone.hollidays.repositories;

import com.ozone.hollidays.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    public Role findByName(String name);
}