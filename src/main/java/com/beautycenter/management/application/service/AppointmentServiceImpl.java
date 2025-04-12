package com.beautycenter.management.application.service;

import com.beautycenter.management.application.dto.AppointmentDto;
import com.beautycenter.management.application.mapper.AppointmentDtoMapper;
import com.beautycenter.management.domain.event.DomainEventPublisher;
import com.beautycenter.management.domain.event.appointment.AppointmentCreatedEvent;
import com.beautycenter.management.domain.model.Appointment;
import com.beautycenter.management.domain.model.AppointmentStatus;
import com.beautycenter.management.domain.model.Company;
import com.beautycenter.management.domain.model.Customer;
import com.beautycenter.management.domain.model.Employee;
import com.beautycenter.management.domain.model.Service;
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
import java.util.UUID;
import java.util.stream.Collectors;

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
    private final AppointmentDtoMapper appointmentDtoMapper;

    /**
     * Creates an appointment from a DTO.
     * 
     * @param dto the appointment DTO
     * @return the created appointment DTO
     */
    public AppointmentDto createAppointmentFromDto(AppointmentDto dto) {
        Appointment appointmentModel = appointmentDtoMapper.toDomain(dto);
        Appointment createdAppointment = createAppointment(appointmentModel);
        return appointmentDtoMapper.toDto(createdAppointment);
    }
    
    /**
     * Finds all appointments and returns them as DTOs.
     * 
     * @return list of appointment DTOs
     */
    public List<AppointmentDto> findAllAppointmentsAsDto() {
        List<Appointment> appointments = findAllAppointments();
        return appointments.stream()
                .map(appointmentDtoMapper::toDto)
                .collect(Collectors.toList());
    }
    
    /**
     * Finds an appointment by ID and returns it as a DTO.
     * 
     * @param id the appointment ID
     * @return optional containing the appointment DTO if found
     */
    public Optional<AppointmentDto> findByIdAsDto(UUID id) {
        return findById(id).map(appointmentDtoMapper::toDto);
    }
    
    /**
     * Updates an appointment from a DTO.
     * 
     * @param dto the appointment DTO
     * @return the updated appointment DTO
     */
    public AppointmentDto updateAppointmentFromDto(AppointmentDto dto) {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("Appointment ID cannot be null for update operation");
        }
        
        Appointment appointmentModel = appointmentDtoMapper.toDomain(dto);
        Appointment updatedAppointment = updateAppointment(appointmentModel);
        return appointmentDtoMapper.toDto(updatedAppointment);
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {
        // Set status to REQUESTED if not specified
        if (appointment.getStatus() == null) {
            appointment.setStatus(AppointmentStatus.REQUESTED);
        }
        
        // Validate time slot availability
        if (!isTimeSlotAvailable(
                appointment.getCompanyId() != null ? new Company(appointment.getCompanyId(), null, null, null, null, null, null, null, null, null, null, null, false, null, null) : null, 
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
    public Optional<Appointment> findById(UUID id) {
        return appointmentRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Appointment> findAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Appointment> findAppointmentsByCustomer(Customer customer) {
        return appointmentRepository.findByCustomer(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Appointment> findAppointmentsByEmployee(Employee employee) {
        return appointmentRepository.findByEmployee(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Appointment> findAppointmentsByCompany(Company company) {
        return appointmentRepository.findByCompanyId(company.getId());
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
        return appointmentRepository.findByCompanyIdAndStartTimeBetween(company.getId(), start, end);
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
    public void deleteAppointment(UUID id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public Appointment cancelAppointment(UUID id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found with ID: " + id));
        
        appointment.cancel();
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment completeAppointment(UUID id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found with ID: " + id));
        
        appointment.completeAppointment();
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment addService(UUID appointmentId, Service service) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found with ID: " + appointmentId));
        
        appointment.addService(service.getId());
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment removeService(UUID appointmentId, Service service) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found with ID: " + appointmentId));
        
        appointment.removeService(service.getId());
        return appointmentRepository.save(appointment);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isTimeSlotAvailable(Company company, Employee employee, LocalDateTime start, LocalDateTime end) {
        if (company == null || employee == null || start == null || end == null) {
            return false;
        }
        
        // Check if there are any overlapping appointments for the employee in the given time slot
        List<Appointment> overlappingAppointments = appointmentRepository.findOverlappingAppointments(
                company.getId(), employee.getId(), start, end);
        
        return overlappingAppointments.isEmpty();
    }
    
    /**
     * Cancels an appointment and returns the result as a DTO.
     * 
     * @param appointmentId the appointment ID
     * @return the updated appointment DTO
     */
    public AppointmentDto cancelAppointmentAsDto(UUID appointmentId) {
        Appointment appointment = cancelAppointment(appointmentId);
        return appointmentDtoMapper.toDto(appointment);
    }
    
    /**
     * Completes an appointment and returns the result as a DTO.
     * 
     * @param appointmentId the appointment ID
     * @return the updated appointment DTO
     */
    public AppointmentDto completeAppointmentAsDto(UUID appointmentId) {
        Appointment appointment = completeAppointment(appointmentId);
        return appointmentDtoMapper.toDto(appointment);
    }
}