package com.beautycenter.management.domain.service;

import com.beautycenter.management.domain.model.Appointment;
import com.beautycenter.management.domain.model.AppointmentStatus;
import com.beautycenter.management.domain.model.Company;
import com.beautycenter.management.domain.model.Service;
import com.beautycenter.management.domain.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for appointment-related business logic.
 * This is part of the domain layer in DDD and provides the application with access to domain logic.
 */
public interface AppointmentService {
    
    /**
     * Create a new appointment
     */
    Appointment createAppointment(Appointment appointment);
    
    /**
     * Find an appointment by ID
     */
    Optional<Appointment> findById(Long id);
    
    /**
     * Find all appointments
     */
    List<Appointment> findAllAppointments();
    
    /**
     * Find appointments by customer
     */
    List<Appointment> findAppointmentsByCustomer(User customer);
    
    /**
     * Find appointments by employee
     */
    List<Appointment> findAppointmentsByEmployee(User employee);
    
    /**
     * Find appointments by company
     */
    List<Appointment> findAppointmentsByCompany(Company company);
    
    /**
     * Find appointments by status
     */
    List<Appointment> findAppointmentsByStatus(AppointmentStatus status);
    
    /**
     * Find appointments by date range
     */
    List<Appointment> findAppointmentsByDateRange(LocalDateTime start, LocalDateTime end);
    
    /**
     * Find appointments by company and date range
     */
    List<Appointment> findAppointmentsByCompanyAndDateRange(Company company, LocalDateTime start, LocalDateTime end);
    
    /**
     * Find appointments for a specific date
     */
    List<Appointment> findAppointmentsByDate(LocalDate date);
    
    /**
     * Update an appointment
     */
    Appointment updateAppointment(Appointment appointment);
    
    /**
     * Delete an appointment
     */
    void deleteAppointment(Long id);
    
    /**
     * Cancel an appointment
     */
    Appointment cancelAppointment(Long id);
    
    /**
     * Complete an appointment
     */
    Appointment completeAppointment(Long id);
    
    /**
     * Add a service to an appointment
     */
    Appointment addService(Long appointmentId, Service service);
    
    /**
     * Remove a service from an appointment
     */
    Appointment removeService(Long appointmentId, Service service);
    
    /**
     * Check if a time slot is available for a new appointment
     */
    boolean isTimeSlotAvailable(Company company, User employee, LocalDateTime start, LocalDateTime end);
}