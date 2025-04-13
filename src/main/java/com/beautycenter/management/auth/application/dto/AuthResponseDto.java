package com.beautycenter.management.auth.application.dto;

import com.beautycenter.management.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Data Transfer Object for authentication responses.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDto {
    
    private String token;
    private String type;
    private Long id;
    private String username;
    private String email;
    private Set<Role> roles;
    
    public AuthResponseDto(String token, Long id, String username, String email, Set<Role> roles) {
        this.token = token;
        this.type = "Bearer";
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}