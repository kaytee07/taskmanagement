package com.project.taskmanagement.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "role_id")
    private UUID roleId;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Column(name = "description")
    private String description;

    public UUID getRoleId() {
        return roleId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setRoleId(UUID roleId) {
        this.roleId = roleId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

