package com.beautycenter.management.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Domain model representing a service offered by the beauty center.
 * This is a core domain entity in the Beauty Center Management system.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer durationMinutes;
    private String category;
    private String imageUrl;
    
    @Builder.Default
    private boolean active = true;
    
    private Long companyId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    /**
     * Checks if the service is valid for booking.
     *
     * @return true if the service is valid for booking, false otherwise
     */
    public boolean isValidForBooking() {
        return id != null && 
               name != null && !name.isBlank() &&
               price != null && price.compareTo(BigDecimal.ZERO) > 0 &&
               durationMinutes != null && durationMinutes > 0 &&
               active;
    }
    
    /**
     * Gets the formatted price of the service.
     *
     * @return the formatted price
     */
    public String getFormattedPrice() {
        return "$" + price.toString();
    }
    
    /**
     * Gets the formatted duration of the service.
     *
     * @return the formatted duration
     */
    public String getFormattedDuration() {
        if (durationMinutes == null) {
            return "N/A";
        }
        
        if (durationMinutes < 60) {
            return durationMinutes + " min";
        }
        
        int hours = durationMinutes / 60;
        int minutes = durationMinutes % 60;
        
        if (minutes == 0) {
            return hours + " hr";
        }
        
        return hours + " hr " + minutes + " min";
    }
}