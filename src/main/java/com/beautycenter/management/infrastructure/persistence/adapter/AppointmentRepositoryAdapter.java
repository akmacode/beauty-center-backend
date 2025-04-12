package com.beautycenter.management.infrastructure.persistence.adapter;

import com.beautycenter.management.domain.model.Appointment;
import com.beautycenter.management.domain.model.AppointmentStatus;
import com.beautycenter.management.domain.model.Customer;
import com.beautycenter.management.domain.model.Employee;
import com.beautycenter.management.domain.repository.AppointmentRepository;
import com.beautycenter.management.infrastructure.persistence.entity.CustomerEntity;
import com.beautycenter.management.infrastructure.persistence.entity.EmployeeEntity;
import com.beautycenter.management.infrastructure.persistence.mapper.AppointmentEntityMapper;
import com.beautycenter.management.infrastructure.persistence.mapper.CustomerEntityMapper;
import com.beautycenter.management.infrastructure.persistence.mapper.EmployeeEntityMapper;
import com.beautycenter.management.infrastructure.persistence.repository.JpaAppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Adapter implementation of the AppointmentRepository interface.
 * Connects the domain repository to the JPA repository.
 */
@Component
@RequiredArgsConstructor
public class AppointmentRepositoryAdapter implements AppointmentRepository {

    private final JpaAppointmentRepository jpaRepository;
    private final AppointmentEntityMapper appointmentMapper;
    private final CustomerEntityMapper customerMapper;
    private final EmployeeEntityMapper employeeMapper;

    @Override
    public Appointment save(Appointment appointment) {
        var entity = appointmentMapper.toEntity(appointment);
        var savedEntity = jpaRepository.save(entity);
        return appointmentMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Appointment> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(appointmentMapper::toDomain);
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentMapper.toDomainList(jpaRepository.findAll());
    }

    @Override
    public List<Appointment> findByCustomer(Customer customer) {
        CustomerEntity customerEntity = customerMapper.toEntity(customer);
        return appointmentMapper.toDomainList(jpaRepository.findByCustomer(customerEntity));
    }

    @Override
    public List<Appointment> findByEmployee(Employee employee) {
        EmployeeEntity employeeEntity = employeeMapper.toEntity(employee);
        return appointmentMapper.toDomainList(jpaRepository.findByEmployee(employeeEntity));
    }

    @Override
    public List<Appointment> findByCompanyId(UUID companyId) {
        return appointmentMapper.toDomainList(jpaRepository.findByCompanyId(companyId));
    }

    @Override
    public List<Appointment> findByStatus(AppointmentStatus status) {
        return appointmentMapper.toDomainList(jpaRepository.findByStatus(status.name()));
    }

    @Override
    public List<Appointment> findByCompanyIdAndStatus(UUID companyId, AppointmentStatus status) {
        return appointmentMapper.toDomainList(
                jpaRepository.findByCompanyIdAndStatus(companyId, status.name()));
    }

    @Override
    public List<Appointment> findByStartTimeBetween(LocalDateTime start, LocalDateTime end) {
        return appointmentMapper.toDomainList(jpaRepository.findByStartTimeBetween(start, end));
    }

    @Override
    public List<Appointment> findByEmployeeIdAndStartTimeBetween(UUID employeeId, LocalDateTime start, LocalDateTime end) {
        return appointmentMapper.toDomainList(
                jpaRepository.findByEmployeeIdAndStartTimeBetween(employeeId, start, end));
    }

    @Override
    public List<Appointment> findByCompanyIdAndStartTimeBetween(UUID companyId, LocalDateTime start, LocalDateTime end) {
        return appointmentMapper.toDomainList(
                jpaRepository.findByCompanyIdAndStartTimeBetween(companyId, start, end));
    }

    @Override
    public List<Appointment> findOverlappingAppointments(UUID companyId, UUID employeeId, LocalDateTime start, LocalDateTime end) {
        return appointmentMapper.toDomainList(
                jpaRepository.findOverlappingAppointments(companyId, employeeId, start, end));
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return jpaRepository.existsById(id);
    }
}