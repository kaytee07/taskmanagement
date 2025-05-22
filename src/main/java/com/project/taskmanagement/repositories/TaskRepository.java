package com.project.taskmanagement.repositories;

import com.project.taskmanagement.entities.Task;
import com.project.taskmanagement.entities.User;
import com.project.taskmanagement.entities.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findByAssignedTo(User assignedTo);

    List<Task> findByAssignedBy(User assignedBy);

    List<Task> findByStatus(TaskStatus status);

    List<Task> findByDueDateBefore(java.time.LocalDate dueDate);

    List<Task> findByAssignedToAndStatus(User assignedTo, TaskStatus status);

}

