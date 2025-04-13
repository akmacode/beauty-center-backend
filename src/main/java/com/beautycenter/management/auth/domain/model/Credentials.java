package com.beautycenter.management.auth.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain model representing user credentials for authentication.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Credentials {
    
    private String username;
    private String password;
    
    /**
     * Validates that the credentials are not empty.
     *
     * @return true if valid, false otherwise
     */
    public boolean isValid() {
        return username != null && !username.isBlank() && 
               password != null && !password.isBlank();
    }
}