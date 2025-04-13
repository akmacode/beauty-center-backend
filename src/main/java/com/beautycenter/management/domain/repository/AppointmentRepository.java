package com.beautycenter.management.domain.repository;

import com.beautycenter.management.domain.model.Appointment;
import com.beautycenter.management.domain.model.AppointmentStatus;
import com.beautycenter.management.domain.model.Customer;
import com.beautycenter.management.domain.model.Employee;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    Optional<Appointment> findById(UUID id);
    
    /**
     * Find all appointments.
     *
     * @return list of all appointments
     */
    List<Appointment> findAll();
    
    /**
     * Find appointments by customer.
     *
     * @param customer the customer
     * @return list of appointments for the customer
     */
    List<Appointment> findByCustomer(Customer customer);
    
    /**
     * Find appointments by employee.
     *
     * @param employee the employee
     * @return list of appointments for the employee
     */
    List<Appointment> findByEmployee(Employee employee);
    
    /**
     * Find appointments by company ID.
     *
     * @param companyId the company ID
     * @return list of appointments for the company
     */
    List<Appointment> findByCompanyId(UUID companyId);
    
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
    List<Appointment> findByCompanyIdAndStatus(UUID companyId, AppointmentStatus status);
    
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
    List<Appointment> findByEmployeeIdAndStartTimeBetween(UUID employeeId, LocalDateTime start, LocalDateTime end);
    
    /**
     * Find appointments by company ID between start and end times.
     *
     * @param companyId the company ID
     * @param start the start time
     * @param end the end time
     * @return list of appointments for the company in the time range
     */
    List<Appointment> findByCompanyIdAndStartTimeBetween(UUID companyId, LocalDateTime start, LocalDateTime end);
    
    /**
     * Find overlapping appointments for an employee in a given time slot.
     *
     * @param companyId the company ID
     * @param employeeId the employee ID
     * @param start the start time
     * @param end the end time
     * @return list of overlapping appointments
     */
    List<Appointment> findOverlappingAppointments(UUID companyId, UUID employeeId, LocalDateTime start, LocalDateTime end);
    
    /**
     * Delete an appointment by ID.
     *
     * @param id the appointment ID
     */
    void deleteById(UUID id);
    
    /**
     * Check if an appointment exists by ID.
     *
     * @param id the appointment ID
     * @return true if exists, false otherwise
     */
    boolean existsById(UUID id);
}