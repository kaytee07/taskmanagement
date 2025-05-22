package com.project.taskmanagement.repositories;

import com.project.taskmanagement.entities.TaskReviewRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskReviewRequestRepository extends JpaRepository<TaskReviewRequest, UUID> {
    List<TaskReviewRequest> findByStatus(String status);
}

