package com.beautycenter.management.domain.service;

import com.beautycenter.management.domain.model.Role;
import com.beautycenter.management.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service interface for User operations in the domain layer.
 * This interface should only work with domain model objects, not DTOs.
 */
public interface UserService {
    
    /**
     * Create a new user.
     *
     * @param user the user data
     * @return the created user
     */
    User createUser(User user);
    
    /**
     * Find user by ID.
     *
     * @param id the user ID
     * @return optional containing the user if found
     */
    Optional<User> findById(Long id);
    
    /**
     * Find user by username.
     *
     * @param username the username
     * @return optional containing the user if found
     */
    Optional<User> findByUsername(String username);
    
    /**
     * Find user by email.
     *
     * @param email the email
     * @return optional containing the user if found
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
     * @return list of users for the company
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
     * @param roles the roles
     * @return list of users with any of the specified roles
     */
    List<User> findByRolesIn(Set<Role> roles);
    
    /**
     * Update user.
     *
     * @param id the user ID
     * @param user the updated user data
     * @return the updated user
     */
    User updateUser(Long id, User user);
    
    /**
     * Delete user by ID.
     *
     * @param id the user ID
     */
    void deleteById(Long id);
    
    /**
     * Change user active status.
     *
     * @param id the user ID
     * @param active the active status
     * @return the updated user
     */
    User changeStatus(Long id, boolean active);
    
    /**
     * Check if user exists by ID.
     *
     * @param id the user ID
     * @return true if exists, false otherwise
     */
    boolean existsById(Long id);
    
    /**
     * Check if user exists by username.
     *
     * @param username the username
     * @return true if exists, false otherwise
     */
    boolean existsByUsername(String username);
    
    /**
     * Check if user exists by email.
     *
     * @param email the email
     * @return true if exists, false otherwise
     */
    boolean existsByEmail(String email);
    
    /**
     * Change user password.
     *
     * @param id the user ID
     * @param currentPassword the current password (for verification)
     * @param newPassword the new password
     * @return the updated user
     */
    User changePassword(Long id, String currentPassword, String newPassword);
    
    /**
     * Activate a user account.
     *
     * @param id the user ID
     * @return the activated user
     */
    User activateUser(Long id);
    
    /**
     * Deactivate a user account.
     *
     * @param id the user ID
     * @return the deactivated user
     */
    User deactivateUser(Long id);
}