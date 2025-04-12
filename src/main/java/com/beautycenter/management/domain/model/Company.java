package com.beautycenter.management.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Domain model representing a company in the system.
 * This is a core domain entity in the Beauty Center Management system.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    
    private Long id;
    private String name;
    private String description;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String phoneNumber;
    private String email;
    private String website;
    private String logoUrl;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    /**
     * Checks if the company has a valid address.
     *
     * @return true if the address is valid, false otherwise
     */
    public boolean hasValidAddress() {
        return address != null && !address.isBlank() && 
               city != null && !city.isBlank() && 
               state != null && !state.isBlank() && 
               zipCode != null && !zipCode.isBlank() && 
               country != null && !country.isBlank();
    }
    
    /**
     * Gets the full address of the company.
     *
     * @return the full address as a formatted string
     */
    public String getFullAddress() {
        return String.format("%s, %s, %s %s, %s", address, city, state, zipCode, country);
    }
    
    /**
     * Checks if the company has valid contact information.
     *
     * @return true if the contact information is valid, false otherwise
     */
    public boolean hasValidContactInfo() {
        return (phoneNumber != null && !phoneNumber.isBlank()) || 
               (email != null && !email.isBlank());
    }
}