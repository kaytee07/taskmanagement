package com.project.taskmanagement.repositories;

import com.project.taskmanagement.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}

