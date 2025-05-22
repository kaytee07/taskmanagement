package com.project.taskmanagement.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "statuses")
public class TaskStatus {

    @Id
    @Column(name = "status_id", nullable = false, length = 36)
    private String statusId;

    @Column(name = "name", nullable = false, unique = true, length = 60)
    private String name;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;


    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

