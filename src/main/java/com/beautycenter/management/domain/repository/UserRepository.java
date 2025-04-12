package com.beautycenter.management.domain.repository;

import com.beautycenter.management.domain.model.Role;
import com.beautycenter.management.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Repository interface for User domain entity.
 * This is part of the Hexagonal Architecture's port.
 */
public interface UserRepository {
    
    /**
     * Save a user.
     *
     * @param user the user to save
     * @return the saved user
     */
    User save(User user);
    
    /**
     * Find a user by ID.
     *
     * @param id the user ID
     * @return an Optional containing the user if found, empty otherwise
     */
    Optional<User> findById(Long id);
    
    /**
     * Find a user by username.
     *
     * @param username the username
     * @return an Optional containing the user if found, empty otherwise
     */
    Optional<User> findByUsername(String username);
    
    /**
     * Find a user by email.
     *
     * @param email the email
     * @return an Optional containing the user if found, empty otherwise
     */
    Optional<User> findByEmail(String email);
    
    /**
     * Find all users.
     *
     * @return list of all users
     */
    List<User> findAll();
    
    /**
     * Find users by company ID.
     *
     * @param companyId the company ID
     * @return list of users belonging to the company
     */
    List<User> findByCompanyId(Long companyId);
    
    /**
     * Find users by role.
     *
     * @param role the role
     * @return list of users with the specified role
     */
    List<User> findByRole(Role role);
    
    /**
     * Find users by roles.
     *
     * @param roles the set of roles
     * @return list of users with any of the specified roles
     */
    List<User> findByRolesIn(Set<Role> roles);
    
    /**
     * Delete a user by ID.
     *
     * @param id the user ID
     */
    void deleteById(Long id);
    
    /**
     * Check if a username exists.
     *
     * @param username the username
     * @return true if exists, false otherwise
     */
    boolean existsByUsername(String username);
    
    /**
     * Check if an email exists.
     *
     * @param email the email
     * @return true if exists, false otherwise
     */
    boolean existsByEmail(String email);
}