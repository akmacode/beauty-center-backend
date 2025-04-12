package com.beautycenter.management.infrastructure.persistence.adapter;

import com.beautycenter.management.domain.model.Appointment;
import com.beautycenter.management.domain.model.AppointmentStatus;
import com.beautycenter.management.domain.repository.AppointmentRepository;
import com.beautycenter.management.infrastructure.persistence.entity.AppointmentEntity;
import com.beautycenter.management.infrastructure.persistence.mapper.AppointmentMapper;
import com.beautycenter.management.infrastructure.persistence.repository.AppointmentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adapter implementation of AppointmentRepository.
 * Bridges the domain with the JPA infrastructure.
 */
@Repository
@RequiredArgsConstructor
public class AppointmentRepositoryAdapter implements AppointmentRepository {
    
    private final AppointmentJpaRepository jpaRepository;
    private final AppointmentMapper mapper;
    
    @Override
    public Appointment save(Appointment appointment) {
        AppointmentEntity entity = mapper.toEntity(appointment);
        AppointmentEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<Appointment> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }
    
    @Override
    public List<Appointment> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Appointment> findByCustomerId(Long customerId) {
        return jpaRepository.findByCustomerId(customerId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Appointment> findByEmployeeId(Long employeeId) {
        return jpaRepository.findByEmployeeId(employeeId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Appointment> findByCompanyId(Long companyId) {
        return jpaRepository.findByCompanyId(companyId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Appointment> findByStatus(AppointmentStatus status) {
        return jpaRepository.findByStatus(status).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Appointment> findByCompanyIdAndStatus(Long companyId, AppointmentStatus status) {
        return jpaRepository.findByCompanyIdAndStatus(companyId, status).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Appointment> findByStartTimeBetween(LocalDateTime start, LocalDateTime end) {
        return jpaRepository.findByStartTimeBetween(start, end).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Appointment> findByEmployeeIdAndStartTimeBetween(Long employeeId, LocalDateTime start, LocalDateTime end) {
        return jpaRepository.findByEmployeeIdAndStartTimeBetween(employeeId, start, end).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Appointment> findByCompanyIdAndStartTimeBetween(Long companyId, LocalDateTime start, LocalDateTime end) {
        return jpaRepository.findByCompanyIdAndStartTimeBetween(companyId, start, end).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }
}