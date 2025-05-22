package com.project.taskmanagement.services;

import com.project.taskmanagement.entities.Permission;
import com.project.taskmanagement.entities.Role;
import com.project.taskmanagement.entities.User;
import com.project.taskmanagement.repositories.PermissionRepository;
import com.project.taskmanagement.repositories.RoleRepository;
import com.project.taskmanagement.repositories.TaskRepository;
import com.project.taskmanagement.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Component
public class UserBootstrapService {
    private static final Logger logger = LoggerFactory.getLogger(UserBootstrapService.class);

    @Value("${app.bootstrap.root-password}")
    private String rootPassword;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init(){
        try {
            createOrGetPermission("Create Task", "permission to create new task");
            createOrGetPermission("Delete Task", "permission delete task");
            createOrGetPermission("Assign Task", "permission assign task");
            createOrGetPermission("Verify Task", "permission verify task");

            if (userRepository.count() == 0){
                logger.info("No users found in the system. Bootstrapping Root User...");
                logger.info("ROOT role not found. Creating new ROOT role with all permissions...");
                Optional<Role> rootRole = roleRepository.findByName("ROOT");
                List<Permission> permissions = permissionRepository.findAll();
                String description = "Root role with all administrative privileges";
                Role roleRoot = createOrGetRole("Root", new HashSet<>(permissions), description);

                User rootUser = new User();
                rootUser.setUserId(UUID.randomUUID());
                rootUser.setFirstName("Root");
                rootUser.setLastName("User");
                rootUser.setEmail("root@taskapp.com");
                rootUser.setPasswordHash(passwordEncoder.encode(rootPassword));
                rootUser.setMustResetPassword(true);
                rootUser.setRoot(true);
                rootUser.setRoleID(roleRoot.getRoleId());
                rootUser.setCreatedBy(null);
                rootUser.setCreatedAt(new Date().toInstant());
                rootUser.setModifiedAt(new Date().toInstant());
                userRepository.save(rootUser);
                logger.info("Root user created with email: root@taskapp.com");

            }


        } catch (Exception e){
            logger.error("Error during user bootstrap: {}", e.getMessage(), e);
        }
    }

    public Role createOrGetRole(String name, Set permissions, String description){
        return roleRepository.findByName(name).orElseGet(() -> {
            Role role = new Role();
            role.setRoleId(UUID.randomUUID());
            role.setName(name);
            role.setDescription(description);
            role.setPermissions(permissions);
            logger.info("permission created");
            return roleRepository.save(role);
        });
    }

    public Permission createOrGetPermission(String name, String description){
        return  permissionRepository.findByName(name).orElseGet(()-> {
            Permission permission = new Permission();
            permission.setPermissionId(UUID.randomUUID());
            permission.setName(name);
            permission.setDescription(description);
            logger.info("permission created");
            return  permissionRepository.save(permission);
        });
    }


}
