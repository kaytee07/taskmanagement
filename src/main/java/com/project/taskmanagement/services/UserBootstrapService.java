package com.project.taskmanagement.services;

import com.project.taskmanagement.entities.Permission;
import com.project.taskmanagement.entities.Role;
import com.project.taskmanagement.repositories.PermissionRepository;
import com.project.taskmanagement.repositories.RoleRepository;
import com.project.taskmanagement.repositories.TaskRepository;
import com.project.taskmanagement.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Component
public class UserBootstrapService {
    private static final Logger logger = LoggerFactory.getLogger(UserBootstrapService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @PostConstruct
    public void init(){
        try {
            if (userRepository.count() == 0){
                logger.info("No users found in the system. Bootstrapping Root User...");
                logger.info("ROOT role not found. Creating new ROOT role with all permissions...");
                Optional<Role> rootRole = roleRepository.findByName("ROOT");
                List<Permission> permissions = permissionRepository.findAll();
                String description = "Root role with all administrative privileges";
                Role roleRoot = createOrGetRole("Root", new HashSet<>(permissions), description);
            }
        } catch (Exception e){

        }
    }

    public Role createOrGetRole(String name, Set permissions, String description){
        return roleRepository.findByName(name).orElseGet(() -> {
            Role role = new Role();
            role.setRoleId(UUID.randomUUID());
            role.setName(name);
            role.setDescription(description);
            role.setPermissions(permissions);

            return roleRepository.save(role);
        });
    }


}
