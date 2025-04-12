package com.beautycenter.management.interfaces.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

/**
 * Data Transfer Object for Company entity.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    
    private UUID id;
    
    @NotBlank(message = "Company name is required")
    @Size(min = 2, max = 100, message = "Company name must be between 2 and 100 characters")
    private String name;
    
    private String description;
    
    private String logoUrl;
    
    private String address;
    
    private String city;
    
    private String state;
    
    private String country;
    
    private String zipCode;
    
    private String phoneNumber;
    
    @Email(message = "Email should be valid")
    private String email;
    
    private String website;
    
    private boolean active;
    
    private List<LocationDto> locations;
}