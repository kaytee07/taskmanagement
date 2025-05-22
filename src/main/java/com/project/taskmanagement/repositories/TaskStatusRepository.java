package com.project.taskmanagement.repositories;

import com.project.taskmanagement.entities.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, UUID> {
}
