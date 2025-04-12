package com.beautycenter.management.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Domain model representing a physical location of a beauty center.
 * This is a core domain entity in the Beauty Center Management system.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    
    private Long id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String phoneNumber;
    private String email;
    private Double latitude;
    private Double longitude;
    
    @Builder.Default
    private boolean active = true;
    
    private Long companyId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    /**
     * Checks if the location has valid coordinates.
     *
     * @return true if coordinates are valid, false otherwise
     */
    public boolean hasValidCoordinates() {
        return latitude != null && longitude != null;
    }
    
    /**
     * Gets the full address of the location.
     *
     * @return the full address as a formatted string
     */
    public String getFullAddress() {
        return String.format("%s, %s, %s %s, %s", address, city, state, zipCode, country);
    }
    
    /**
     * Checks if the location has a valid address.
     *
     * @return true if address is valid, false otherwise
     */
    public boolean hasValidAddress() {
        return address != null && !address.isBlank() && 
               city != null && !city.isBlank() && 
               state != null && !state.isBlank() && 
               zipCode != null && !zipCode.isBlank() && 
               country != null && !country.isBlank();
    }
    
    /**
     * Checks if the location has valid contact information.
     *
     * @return true if contact information is valid, false otherwise
     */
    public boolean hasValidContactInfo() {
        return (phoneNumber != null && !phoneNumber.isBlank()) || 
               (email != null && !email.isBlank());
    }
}