package com.beautycenter.management.domain.repository;

import com.beautycenter.management.domain.model.Appointment;
import com.beautycenter.management.domain.model.AppointmentStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Appointment domain entity.
 * This is part of the Hexagonal Architecture's port.
 */
public interface AppointmentRepository {
    
    /**
     * Save an appointment.
     *
     * @param appointment the appointment to save
     * @return the saved appointment
     */
    Appointment save(Appointment appointment);
    
    /**
     * Find an appointment by ID.
     *
     * @param id the appointment ID
     * @return an Optional containing the appointment if found, empty otherwise
     */
    Optional<Appointment> findById(Long id);
    
    /**
     * Find all appointments.
     *
     * @return list of all appointments
     */
    List<Appointment> findAll();
    
    /**
     * Find appointments by customer ID.
     *
     * @param customerId the customer ID
     * @return list of appointments for the customer
     */
    List<Appointment> findByCustomerId(Long customerId);
    
    /**
     * Find appointments by employee ID.
     *
     * @param employeeId the employee ID
     * @return list of appointments for the employee
     */
    List<Appointment> findByEmployeeId(Long employeeId);
    
    /**
     * Find appointments by company ID.
     *
     * @param companyId the company ID
     * @return list of appointments for the company
     */
    List<Appointment> findByCompanyId(Long companyId);
    
    /**
     * Find appointments by status.
     *
     * @param status the appointment status
     * @return list of appointments with the specified status
     */
    List<Appointment> findByStatus(AppointmentStatus status);
    
    /**
     * Find appointments by company ID and status.
     *
     * @param companyId the company ID
     * @param status the appointment status
     * @return list of appointments for the company with the specified status
     */
    List<Appointment> findByCompanyIdAndStatus(Long companyId, AppointmentStatus status);
    
    /**
     * Find appointments between start and end times.
     *
     * @param start the start time
     * @param end the end time
     * @return list of appointments in the time range
     */
    List<Appointment> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
    
    /**
     * Find appointments by employee ID between start and end times.
     *
     * @param employeeId the employee ID
     * @param start the start time
     * @param end the end time
     * @return list of appointments for the employee in the time range
     */
    List<Appointment> findByEmployeeIdAndStartTimeBetween(Long employeeId, LocalDateTime start, LocalDateTime end);
    
    /**
     * Find appointments by company ID between start and end times.
     *
     * @param companyId the company ID
     * @param start the start time
     * @param end the end time
     * @return list of appointments for the company in the time range
     */
    List<Appointment> findByCompanyIdAndStartTimeBetween(Long companyId, LocalDateTime start, LocalDateTime end);
    
    /**
     * Delete an appointment by ID.
     *
     * @param id the appointment ID
     */
    void deleteById(Long id);
    
    /**
     * Check if an appointment exists by ID.
     *
     * @param id the appointment ID
     * @return true if exists, false otherwise
     */
    boolean existsById(Long id);
}