package com.beautycenter.management.domain.service;

import com.beautycenter.management.domain.model.User;

import java.util.Optional;

/**
 * Domain service interface for authentication related operations.
 */
public interface AuthService {
    
    /**
     * Authenticate a user with username and password
     * 
     * @param username The username
     * @param password The password
     * @return The authenticated user if credentials are valid
     */
    Optional<User> authenticate(String username, String password);
    
    /**
     * Register a new user
     *
     * @param user User to register
     * @return The registered user
     */
    User register(User user);
    
    /**
     * Generate authentication token for a user
     *
     * @param user The user
     * @return JWT token
     */
    String generateToken(User user);
    
    /**
     * Validate a token
     *
     * @param token JWT token
     * @return Whether the token is valid
     */
    boolean validateToken(String token);
    
    /**
     * Get the username from a token
     *
     * @param token JWT token
     * @return Username
     */
    String getUsernameFromToken(String token);
}