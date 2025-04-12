package com.beautycenter.management.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Domain model representing an employee in the system.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String role;
    private UUID companyId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    /**
     * Gets the full name of the employee.
     * 
     * @return the full name
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
}