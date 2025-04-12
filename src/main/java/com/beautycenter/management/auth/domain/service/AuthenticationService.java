package com.beautycenter.management.auth.domain.service;

import com.beautycenter.management.auth.domain.model.AuthToken;
import com.beautycenter.management.auth.domain.model.Credentials;
import com.beautycenter.management.domain.model.User;

import java.util.Optional;

/**
 * Domain service interface for authentication operations.
 */
public interface AuthenticationService {
    
    /**
     * Authenticate a user with credentials.
     *
     * @param credentials user credentials
     * @return Optional containing the user if authentication successful, empty otherwise
     */
    Optional<User> authenticate(Credentials credentials);
    
    /**
     * Generate an authentication token for a user.
     *
     * @param user the authenticated user
     * @return the authentication token
     */
    AuthToken generateToken(User user);
    
    /**
     * Validate a token.
     *
     * @param token the token to validate
     * @return true if valid, false otherwise
     */
    boolean validateToken(String token);
    
    /**
     * Get username from token.
     *
     * @param token the token
     * @return the username
     */
    String getUsernameFromToken(String token);
    
    /**
     * Get user ID from token.
     *
     * @param token the token
     * @return the user ID
     */
    Long getUserIdFromToken(String token);
}