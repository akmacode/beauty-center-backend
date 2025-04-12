package com.beautycenter.management.application.service;

import com.beautycenter.management.domain.event.DomainEventPublisher;
import com.beautycenter.management.domain.event.appointment.AppointmentCreatedEvent;
import com.beautycenter.management.domain.model.Appointment;
import com.beautycenter.management.domain.model.AppointmentStatus;
import com.beautycenter.management.domain.model.Company;
import com.beautycenter.management.domain.model.Service;
import com.beautycenter.management.domain.model.User;
import com.beautycenter.management.domain.repository.AppointmentRepository;
import com.beautycenter.management.domain.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the AppointmentService interface.
 * This class is responsible for the business logic related to appointments.
 */
@Component
@RequiredArgsConstructor
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DomainEventPublisher eventPublisher;

    @Override
    public Appointment createAppointment(Appointment appointment) {
        // Set status to SCHEDULED if not specified
        if (appointment.getStatus() == null) {
            appointment.setStatus(AppointmentStatus.SCHEDULED);
        }
        
        // Validate time slot availability
        if (!isTimeSlotAvailable(
                appointment.getCompany(), 
                appointment.getEmployee(), 
                appointment.getStartTime(), 
                appointment.getEndTime())) {
            throw new IllegalStateException("The selected time slot is not available");
        }
        
        Appointment savedAppointment = appointmentRepository.save(appointment);
        
        // Publish appointment created event
        eventPublisher.publish(new AppointmentCreatedEvent(savedAppointment));
        
        return savedAppointment;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Appointment> findById(Long id) {
        return appointmentRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Appointment> findAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Appointment> findAppointmentsByCustomer(User customer) {
        return appointmentRepository.findByCustomer(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Appointment> findAppointmentsByEmployee(User employee) {
        return appointmentRepository.findByEmployee(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Appointment> findAppointmentsByCompany(Company company) {
        return appointmentRepository.findByCompany(company);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Appointment> findAppointmentsByStatus(AppointmentStatus status) {
        return appointmentRepository.findByStatus(status);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Appointment> findAppointmentsByDateRange(LocalDateTime start, LocalDateTime end) {
        return appointmentRepository.findByStartTimeBetween(start, end);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Appointment> findAppointmentsByCompanyAndDateRange(Company company, LocalDateTime start, LocalDateTime end) {
        return appointmentRepository.findByCompanyAndStartTimeBetween(company, start, end);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Appointment> findAppointmentsByDate(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        return appointmentRepository.findByStartTimeBetween(startOfDay, endOfDay);
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        // Ensure the appointment exists
        if (!appointmentRepository.existsById(appointment.getId())) {
            throw new IllegalArgumentException("Appointment not found with ID: " + appointment.getId());
        }
        
        return appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public Appointment cancelAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found with ID: " + id));
        
        appointment.cancel();
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment completeAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found with ID: " + id));
        
        appointment.complete();
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment addService(Long appointmentId, Service service) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found with ID: " + appointmentId));
        
        appointment.addService(service);
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment removeService(Long appointmentId, Service service) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found with ID: " + appointmentId));
        
        appointment.removeService(service);
        return appointmentRepository.save(appointment);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isTimeSlotAvailable(Company company, User employee, LocalDateTime start, LocalDateTime end) {
        // Check if there are any overlapping appointments for the employee in the given time slot
        List<Appointment> overlappingAppointments = appointmentRepository.findOverlappingAppointments(
                company, employee, start, end);
        
        return overlappingAppointments.isEmpty();
    }
}