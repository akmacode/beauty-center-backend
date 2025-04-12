package com.beautycenter.management.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) for Appointment entities.
 * Used to transfer appointment data between the application layer and clients.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {
    
    private UUID id;
    private UUID customerId;
    private String customerName;
    private UUID employeeId;
    private String employeeName;
    private UUID serviceId;
    private String serviceName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private String notes;
    private BigDecimal totalPrice;
    private UUID companyId;
    
    @Builder.Default
    private Set<UUID> additionalServiceIds = new HashSet<>();
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    /**
     * Gets the duration of the appointment in minutes.
     *
     * @return the duration in minutes, or null if start or end time is missing
     */
    public Long getDurationMinutes() {
        if (startTime == null || endTime == null) {
            return null;
        }
        return java.time.Duration.between(startTime, endTime).toMinutes();
    }
}