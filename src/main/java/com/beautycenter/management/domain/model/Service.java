package com.beautycenter.management.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Domain model representing a service offered by the beauty center.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    private UUID id;
    private String name;
    private String description;
    private Duration duration;
    private BigDecimal price;
    private UUID companyId;
    private String category;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    /**
     * Gets the duration in minutes.
     * 
     * @return the duration in minutes
     */
    public Long getDurationMinutes() {
        return duration != null ? duration.toMinutes() : null;
    }
    
    /**
     * Sets the duration in minutes.
     * 
     * @param minutes the duration in minutes
     */
    public void setDurationMinutes(Long minutes) {
        this.duration = minutes != null ? Duration.ofMinutes(minutes) : null;
    }
}