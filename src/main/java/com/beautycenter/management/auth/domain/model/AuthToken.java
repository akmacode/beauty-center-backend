package com.beautycenter.management.auth.domain.model;

import com.beautycenter.management.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;

/**
 * Domain model representing an authentication token.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthToken {
    
    private String tokenValue;
    private String tokenType;
    private Long userId;
    private String username;
    private String email;
    private Set<Role> roles;
    private Instant expiryDate;
    
    /**
     * Check if token is expired.
     *
     * @return true if expired, false otherwise
     */
    public boolean isExpired() {
        return expiryDate != null && expiryDate.isBefore(Instant.now());
    }
    
    /**
     * Get full token with type.
     *
     * @return full token string
     */
    public String getFullToken() {
        return tokenType + " " + tokenValue;
    }
}