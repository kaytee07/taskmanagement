package com.project.taskmanagement.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "permissions")
public class Permission {

    @Id
    @Column(name = "permission_id", nullable = false)
    private UUID permissionId;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    public UUID getPermissionId() {
        return permissionId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setPermissionId(UUID permissionId) {
        this.permissionId = permissionId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

