package com.beautycenter.management.auth.domain.service;

import com.beautycenter.management.auth.domain.model.RegistrationRequest;
import com.beautycenter.management.domain.model.User;

/**
 * Domain service interface for user registration operations.
 */
public interface RegistrationService {
    
    /**
     * Registers a new user.
     *
     * @param request registration request
     * @return registered user
     * @throws IllegalArgumentException if the request is invalid or username/email already exists
     */
    User register(RegistrationRequest request) throws IllegalArgumentException;
    
    /**
     * Checks if a username already exists.
     *
     * @param username username to check
     * @return true if exists, false otherwise
     */
    boolean usernameExists(String username);
    
    /**
     * Checks if an email already exists.
     *
     * @param email email to check
     * @return true if exists, false otherwise
     */
    boolean emailExists(String email);
}