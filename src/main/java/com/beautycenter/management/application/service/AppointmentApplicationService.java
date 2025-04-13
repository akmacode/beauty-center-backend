package com.beautycenter.management.application.service;

import com.beautycenter.management.domain.model.Appointment;
import com.beautycenter.management.domain.model.AppointmentStatus;
import com.beautycenter.management.domain.service.AppointmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Application service for Appointment entity.
 * Acts as an adapter between the domain layer and the interface layer.
 */
@Service
@Transactional
public class AppointmentApplicationService {
    
    private final AppointmentService appointmentService;
    
    /**
     * Constructor with dependencies
     * 
     * @param appointmentService the appointment service
     */
    public AppointmentApplicationService(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
    
    /**
     * Create a new appointment.
     *
     * @param appointment the appointment to create
     * @return the created appointment
     */
    public Appointment createAppointment(Appointment appointment) {
        return appointmentService.createAppointment(appointment);
    }
    
    /**
     * Find an appointment by its ID.
     *
     * @param id the appointment ID
     * @return the appointment if found
     */
    @Transactional(readOnly = true)
    public Optional<Appointment> findById(UUID id) {
        return appointmentService.findById(id);
    }
    
    /**
     * Update an appointment.
     *
     * @param id the appointment ID
     * @param appointment the updated appointment data
     * @return the updated appointment
     */
    public Appointment updateAppointment(UUID id, Appointment appointment) {
        return appointmentService.updateAppointment(id, appointment);
    }
    
    /**
     * Delete an appointment.
     *
     * @param id the appointment ID
     */
    public void deleteAppointment(UUID id) {
        appointmentService.deleteAppointment(id);
    }
    
    /**
     * Change appointment status.
     *
     * @param id the appointment ID
     * @param status the new status
     * @return the updated appointment
     */
    public Appointment changeAppointmentStatus(UUID id, AppointmentStatus status) {
        return appointmentService.changeAppointmentStatus(id, status);
    }
    
    /**
     * Find appointments by company ID.
     *
     * @param companyId the company ID
     * @return list of appointments
     */
    @Transactional(readOnly = true)
    public List<Appointment> findByCompanyId(UUID companyId) {
        return appointmentService.findByCompanyId(companyId);
    }
    
    /**
     * Find appointments by customer ID.
     *
     * @param customerId the customer ID
     * @return list of appointments
     */
    @Transactional(readOnly = true)
    public List<Appointment> findByCustomerId(UUID customerId) {
        return appointmentService.findByCustomerId(customerId);
    }
    
    /**
     * Find appointments by employee ID.
     *
     * @param employeeId the employee ID
     * @return list of appointments
     */
    @Transactional(readOnly = true)
    public List<Appointment> findByEmployeeId(UUID employeeId) {
        return appointmentService.findByEmployeeId(employeeId);
    }
    
    /**
     * Find appointments by service ID.
     *
     * @param serviceId the service ID
     * @return list of appointments
     */
    @Transactional(readOnly = true)
    public List<Appointment> findByServiceId(UUID serviceId) {
        return appointmentService.findByServiceId(serviceId);
    }
    
    /**
     * Find appointments by status.
     *
     * @param status the appointment status
     * @return list of appointments
     */
    @Transactional(readOnly = true)
    public List<Appointment> findByStatus(AppointmentStatus status) {
        return appointmentService.findByStatus(status);
    }
    
    /**
     * Find appointments in a date range.
     *
     * @param startDate the start date
     * @param endDate the end date
     * @return list of appointments
     */
    @Transactional(readOnly = true)
    public List<Appointment> findByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return appointmentService.findByDateRange(startDate, endDate);
    }
    
    /**
     * Check if a time slot is available.
     *
     * @param companyId the company ID
     * @param startTime the start time
     * @param endTime the end time
     * @param employeeId the employee ID
     * @return true if the slot is available
     */
    @Transactional(readOnly = true)
    public boolean isTimeSlotAvailable(UUID companyId, LocalDateTime startTime, LocalDateTime endTime, UUID employeeId) {
        return appointmentService.isTimeSlotAvailable(companyId, startTime, endTime, employeeId);
    }
}