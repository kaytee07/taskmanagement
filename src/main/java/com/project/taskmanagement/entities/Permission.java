package com.project.taskmanagement.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "permissions")
public class Permission {

    @Id
    @Column(name = "permission_id")
    private UUID permissionId;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany(mappedBy = "permissions")
    Set<Role> roles = new HashSet<>();

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return  false;
        Permission that = (Permission) o;

        if(permissionId == null || that.permissionId == null)
            return  false;

        return  permissionId.equals(that.permissionId);
    }

    @Override
    public  int hashCode(){
        return permissionId != null ? permissionId.hashCode() : 0;
    }
}

