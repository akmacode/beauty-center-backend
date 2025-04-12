package com.beautycenter.management.interfaces.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 * Data Transfer Object for Location entity.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {
    
    private UUID id;
    
    @NotBlank(message = "Location name is required")
    private String name;
    
    private String address;
    
    private String city;
    
    private String state;
    
    private String country;
    
    private String zipCode;
    
    private String phoneNumber;
    
    @Email(message = "Email should be valid")
    private String email;
    
    private boolean active;
    
    @NotNull(message = "Company ID is required")
    private UUID companyId;
    
    private List<ServiceDto> services;
    
    private List<UserDto> employees;
}