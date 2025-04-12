package com.beautycenter.management.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Domain model representing a company in the system.
 * A company is a beauty center or salon entity that uses the platform.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    private UUID id;
    private String name;
    private String description;
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String phoneNumber;
    private String email;
    private String website;
    private String logoUrl;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}