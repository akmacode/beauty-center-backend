package com.beautycenter.management.domain.model;

/**
 * Enum representing the possible statuses of an appointment.
 * This is used in the Appointment domain model.
 */
public enum AppointmentStatus {
    /**
     * Appointment has been requested but not confirmed.
     */
    REQUESTED,
    
    /**
     * Appointment has been confirmed.
     */
    CONFIRMED,
    
    /**
     * Appointment is currently in progress.
     */
    IN_PROGRESS,
    
    /**
     * Appointment has been completed.
     */
    COMPLETED,
    
    /**
     * Appointment has been cancelled.
     */
    CANCELLED,
    
    /**
     * Customer did not show up for the appointment.
     */
    NO_SHOW
}