package com.project.taskmanagement.repositories;

import com.project.taskmanagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByFirstName(String firstName);
    Optional<User> findByLastName(String lastName);
    Optional<User> findByEmail(String email);
    Optional<User> findByFirstNameContainingIgnoreCase(String keyword);
    Optional<User> findByLastNameContainingIgnoreCase(String keyword);
}
