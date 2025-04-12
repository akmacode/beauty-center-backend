package com.beautycenter.management.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Domain model representing an appointment in the system.
 * This is a core domain entity in the Beauty Center Management system.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    
    private Long id;
    private Long customerId;
    private Long employeeId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String notes;
    
    @Builder.Default
    private AppointmentStatus status = AppointmentStatus.REQUESTED;
    
    @Builder.Default
    private Set<Long> serviceIds = new HashSet<>();
    
    private BigDecimal totalPrice;
    private Long companyId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    /**
     * Checks if the appointment is scheduled in the future.
     *
     * @return true if the appointment is in the future, false otherwise
     */
    public boolean isFuture() {
        return startTime != null && startTime.isAfter(LocalDateTime.now());
    }
    
    /**
     * Checks if the appointment is currently in progress.
     *
     * @return true if the appointment is in progress, false otherwise
     */
    public boolean isInProgress() {
        LocalDateTime now = LocalDateTime.now();
        return startTime != null && endTime != null && 
               now.isAfter(startTime) && now.isBefore(endTime);
    }
    
    /**
     * Gets the duration of the appointment.
     *
     * @return the duration in minutes
     */
    public Long getDurationMinutes() {
        if (startTime == null || endTime == null) {
            return null;
        }
        
        return Duration.between(startTime, endTime).toMinutes();
    }
    
    /**
     * Confirms the appointment.
     */
    public void confirm() {
        if (status == AppointmentStatus.REQUESTED) {
            status = AppointmentStatus.CONFIRMED;
        }
    }
    
    /**
     * Cancels the appointment.
     */
    public void cancel() {
        if (status != AppointmentStatus.COMPLETED && status != AppointmentStatus.NO_SHOW) {
            status = AppointmentStatus.CANCELLED;
        }
    }
    
    /**
     * Marks the appointment as in progress.
     */
    public void startAppointment() {
        if (status == AppointmentStatus.CONFIRMED) {
            status = AppointmentStatus.IN_PROGRESS;
        }
    }
    
    /**
     * Completes the appointment.
     */
    public void completeAppointment() {
        if (status == AppointmentStatus.IN_PROGRESS) {
            status = AppointmentStatus.COMPLETED;
        }
    }
    
    /**
     * Marks the appointment as no-show.
     */
    public void markNoShow() {
        if (status == AppointmentStatus.CONFIRMED) {
            status = AppointmentStatus.NO_SHOW;
        }
    }
    
    /**
     * Adds a service to the appointment.
     *
     * @param serviceId the service ID to add
     */
    public void addService(Long serviceId) {
        if (serviceIds == null) {
            serviceIds = new HashSet<>();
        }
        serviceIds.add(serviceId);
    }
    
    /**
     * Removes a service from the appointment.
     *
     * @param serviceId the service ID to remove
     */
    public void removeService(Long serviceId) {
        if (serviceIds != null) {
            serviceIds.remove(serviceId);
        }
    }
}