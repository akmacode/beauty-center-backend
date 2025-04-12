package com.beautycenter.management.domain.service;

import com.beautycenter.management.domain.model.Appointment;
import com.beautycenter.management.domain.model.AppointmentStatus;
import com.beautycenter.management.domain.model.Company;
import com.beautycenter.management.domain.model.Customer;
import com.beautycenter.management.domain.model.Employee;
import com.beautycenter.management.domain.model.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    Optional<Appointment> findById(UUID id);
    
    /**
     * Find all appointments
     */
    List<Appointment> findAllAppointments();
    
    /**
     * Find appointments by customer
     */
    List<Appointment> findAppointmentsByCustomer(Customer customer);
    
    /**
     * Find appointments by employee
     */
    List<Appointment> findAppointmentsByEmployee(Employee employee);
    
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
    void deleteAppointment(UUID id);
    
    /**
     * Cancel an appointment
     */
    Appointment cancelAppointment(UUID id);
    
    /**
     * Complete an appointment
     */
    Appointment completeAppointment(UUID id);
    
    /**
     * Add a service to an appointment
     */
    Appointment addService(UUID appointmentId, Service service);
    
    /**
     * Remove a service from an appointment
     */
    Appointment removeService(UUID appointmentId, Service service);
    
    /**
     * Check if a time slot is available for a new appointment
     */
    boolean isTimeSlotAvailable(Company company, Employee employee, LocalDateTime start, LocalDateTime end);
}