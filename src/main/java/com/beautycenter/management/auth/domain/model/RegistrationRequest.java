package com.beautycenter.management.auth.domain.model;

import com.beautycenter.management.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Domain model representing a user registration request.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {
    
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Set<Role> roles;
    
    /**
     * Validates that the required fields are not empty.
     *
     * @return true if valid, false otherwise
     */
    public boolean isValid() {
        return username != null && !username.isBlank() &&
               password != null && !password.isBlank() &&
               email != null && !email.isBlank() &&
               firstName != null && !firstName.isBlank() &&
               lastName != null && !lastName.isBlank();
    }
}