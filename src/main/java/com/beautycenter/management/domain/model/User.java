package com.beautycenter.management.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Domain model representing a user in the system.
 * This is a core domain entity in the Beauty Center Management system.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    private Long id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    
    @Builder.Default
    private Set<Role> roles = new HashSet<>();
    
    @Builder.Default
    private boolean active = true;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long companyId;
    
    /**
     * Checks if the user has a specific role.
     *
     * @param role the role to check
     * @return true if the user has the role, false otherwise
     */
    public boolean hasRole(Role role) {
        return roles.contains(role);
    }
    
    /**
     * Checks if the user is an admin.
     *
     * @return true if the user is an admin, false otherwise
     */
    public boolean isAdmin() {
        return hasRole(Role.ADMIN);
    }
    
    /**
     * Checks if the user is an employee.
     *
     * @return true if the user is an employee, false otherwise
     */
    public boolean isEmployee() {
        return hasRole(Role.EMPLOYEE);
    }
    
    /**
     * Checks if the user is a receptionist.
     *
     * @return true if the user is a receptionist, false otherwise
     */
    public boolean isReceptionist() {
        return hasRole(Role.RECEPTIONIST);
    }
    
    /**
     * Checks if the user is a standardist.
     *
     * @return true if the user is a standardist, false otherwise
     */
    public boolean isStandardist() {
        return hasRole(Role.STANDARDIST);
    }
    
    /**
     * Gets the full name of the user.
     *
     * @return the full name (firstName + lastName)
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    /**
     * Adds a role to the user.
     *
     * @param role the role to add
     * @return true if the role was added, false if it was already present
     */
    public boolean addRole(Role role) {
        if (roles == null) {
            roles = new HashSet<>();
        }
        return roles.add(role);
    }
    
    /**
     * Removes a role from the user.
     *
     * @param role the role to remove
     * @return true if the role was removed, false if it wasn't present
     */
    public boolean removeRole(Role role) {
        if (roles != null) {
            return roles.remove(role);
        }
        return false;
    }
    
    /**
     * Adds a role to the user by string name.
     *
     * @param roleName the role name to add
     * @return true if the role was added, false otherwise
     */
    public boolean addRole(String roleName) {
        try {
            Role role = Role.valueOf(roleName);
            return addRole(role);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    
    /**
     * Removes a role from the user by string name.
     *
     * @param roleName the role name to remove
     * @return true if the role was removed, false otherwise
     */
    public boolean removeRole(String roleName) {
        try {
            Role role = Role.valueOf(roleName);
            return removeRole(role);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}