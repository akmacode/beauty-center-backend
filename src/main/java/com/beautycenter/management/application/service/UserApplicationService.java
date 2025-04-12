package com.beautycenter.management.application.service;

import com.beautycenter.management.domain.model.User;
import com.beautycenter.management.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Application service for User entity.
 * Acts as an adapter between the domain layer and the interface layer.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UserApplicationService {
    
    private final UserService userService;
    
    /**
     * Register a new user.
     *
     * @param user the user to register
     * @return the registered user
     */
    public User registerUser(User user) {
        return userService.registerUser(user);
    }
    
    /**
     * Find a user by their ID.
     *
     * @param id the user ID
     * @return the user if found
     */
    @Transactional(readOnly = true)
    public Optional<User> findById(UUID id) {
        return userService.findById(id);
    }
    
    /**
     * Find a user by their username.
     *
     * @param username the username
     * @return the user if found
     */
    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        return userService.findByUsername(username);
    }
    
    /**
     * Find a user by their email.
     *
     * @param email the email
     * @return the user if found
     */
    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return userService.findByEmail(email);
    }
    
    /**
     * Update a user.
     *
     * @param id the user ID to update
     * @param user the updated user data
     * @return the updated user
     */
    public User updateUser(UUID id, User user) {
        return userService.updateUser(id, user);
    }
    
    /**
     * Find all users.
     *
     * @return list of all users
     */
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userService.findAll();
    }
    
    /**
     * Delete a user.
     *
     * @param id the user ID to delete
     */
    public void deleteUser(UUID id) {
        userService.deleteUser(id);
    }
    
    /**
     * Deactivate a user.
     *
     * @param id the user ID to deactivate
     * @return the deactivated user
     */
    public User deactivateUser(UUID id) {
        return userService.deactivateUser(id);
    }
    
    /**
     * Activate a user.
     *
     * @param id the user ID to activate
     * @return the activated user
     */
    public User activateUser(UUID id) {
        return userService.activateUser(id);
    }
    
    /**
     * Change a user's password.
     *
     * @param id the user ID
     * @param currentPassword the current password
     * @param newPassword the new password
     * @return the updated user
     */
    public User changePassword(UUID id, String currentPassword, String newPassword) {
        return userService.changePassword(id, currentPassword, newPassword);
    }
}