package com.beautycenter.management.domain.service;

import com.beautycenter.management.application.dto.UserDTO;
import com.beautycenter.management.domain.model.Role;

import java.util.List;
import java.util.Set;

/**
 * Service interface for User operations.
 */
public interface UserService {
    
    /**
     * Create a new user.
     *
     * @param userDTO the user data
     * @return the created user
     */
    UserDTO createUser(UserDTO userDTO);
    
    /**
     * Get user by ID.
     *
     * @param id the user ID
     * @return the user
     */
    UserDTO getUserById(Long id);
    
    /**
     * Get user by username.
     *
     * @param username the username
     * @return the user
     */
    UserDTO getUserByUsername(String username);
    
    /**
     * Get user by email.
     *
     * @param email the email
     * @return the user
     */
    UserDTO getUserByEmail(String email);
    
    /**
     * Get all users.
     *
     * @return list of all users
     */
    List<UserDTO> getAllUsers();
    
    /**
     * Get users by company ID.
     *
     * @param companyId the company ID
     * @return list of users for the company
     */
    List<UserDTO> getUsersByCompanyId(Long companyId);
    
    /**
     * Get users by role.
     *
     * @param role the role
     * @return list of users with the specified role
     */
    List<UserDTO> getUsersByRole(Role role);
    
    /**
     * Get users by roles.
     *
     * @param roles the roles
     * @return list of users with any of the specified roles
     */
    List<UserDTO> getUsersByRoles(Set<Role> roles);
    
    /**
     * Update user.
     *
     * @param id the user ID
     * @param userDTO the updated user data
     * @return the updated user
     */
    UserDTO updateUser(Long id, UserDTO userDTO);
    
    /**
     * Delete user.
     *
     * @param id the user ID
     */
    void deleteUser(Long id);
    
    /**
     * Change user active status.
     *
     * @param id the user ID
     * @param active the active status
     * @return the updated user
     */
    UserDTO changeUserStatus(Long id, boolean active);
}