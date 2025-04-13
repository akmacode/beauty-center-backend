package com.beautycenter.management.domain.event.appointment;

import com.beautycenter.management.domain.event.AbstractDomainEvent;
import com.beautycenter.management.domain.model.Appointment;
import com.beautycenter.management.domain.model.AppointmentStatus;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Domain event that is published when a new appointment is created.
 */
public class AppointmentCreatedEvent extends AbstractDomainEvent {

    private final UUID appointmentId;
    private final UUID customerId;
    private final UUID employeeId;
    private final UUID serviceId;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
   private final AppointmentStatus status;

    /**
     * Constructs a new AppointmentCreatedEvent.
     *
     * @param appointment the appointment that was created
     */
    public AppointmentCreatedEvent(Appointment appointment) {
        super();
        this.appointmentId = appointment.getId();
        this.customerId = appointment.getCustomer() != null ? appointment.getCustomer().getId() : null;
        this.employeeId = appointment.getEmployee() != null ? appointment.getEmployee().getId() : null;
        this.serviceId = appointment.getService() != null ? appointment.getService().getId() : null;
        this.startTime = appointment.getStartTime();
        this.endTime = appointment.getEndTime();
        this.status = appointment.getStatus();
    }

    /**
     * Constructs a new AppointmentCreatedEvent with specified ID and timestamp.
     *
     * @param eventId      the event ID
     * @param occurredAt   the timestamp when the event occurred
     * @param appointmentId the ID of the appointment that was created
     * @param customerId   the ID of the customer for the appointment
     * @param employeeId   the ID of the employee for the appointment (or null if not assigned)
     * @param serviceId    the ID of the service for the appointment
     * @param startTime    the start time of the appointment
     * @param endTime      the end time of the appointment
     * @param status       the status of the appointment
     */
    public AppointmentCreatedEvent(UUID eventId, Instant occurredAt, UUID appointmentId, UUID customerId, UUID employeeId, 
                                  UUID serviceId, LocalDateTime startTime, LocalDateTime endTime, AppointmentStatus status) {
        super(eventId, occurredAt);
        this.appointmentId = appointmentId;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.serviceId = serviceId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public UUID getAppointmentId() {
        return appointmentId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    public UUID getServiceId() {
        return serviceId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }
}