package com.beautycenter.management.infrastructure.persistence.mapper;

import com.beautycenter.management.domain.model.Appointment;
import com.beautycenter.management.domain.model.AppointmentStatus;
import com.beautycenter.management.infrastructure.persistence.entity.AppointmentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Maps between Appointment domain entities and JPA entities.
 * This provides a clean separation between the domain layer and infrastructure layer.
 */
@Component
@RequiredArgsConstructor
public class AppointmentEntityMapper {

    private final CustomerEntityMapper customerMapper;
    private final EmployeeEntityMapper employeeMapper;
    private final ServiceEntityMapper serviceMapper;
    
    /**
     * Converts a domain Appointment to a JPA AppointmentEntity.
     *
     * @param appointment the domain appointment
     * @return the JPA entity
     */
    public AppointmentEntity toEntity(Appointment appointment) {
        if (appointment == null) {
            return null;
        }
        
        return AppointmentEntity.builder()
                .id(appointment.getId())
                .customer(appointment.getCustomer() != null ? customerMapper.toEntity(appointment.getCustomer()) : null)
                .employee(appointment.getEmployee() != null ? employeeMapper.toEntity(appointment.getEmployee()) : null)
                .service(appointment.getService() != null ? serviceMapper.toEntity(appointment.getService()) : null)
                .startTime(appointment.getStartTime())
                .endTime(appointment.getEndTime())
                .status(appointment.getStatus().name())
                .notes(appointment.getNotes())
                .totalPrice(appointment.getTotalPrice())
                .companyId(appointment.getCompanyId())
                .additionalServiceIds(appointment.getAdditionalServiceIds())
                .createdAt(appointment.getCreatedAt())
                .updatedAt(appointment.getUpdatedAt())
                .build();
    }
    
    /**
     * Converts a JPA AppointmentEntity to a domain Appointment.
     *
     * @param entity the JPA entity
     * @return the domain appointment
     */
    public Appointment toDomain(AppointmentEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return Appointment.builder()
                .id(entity.getId())
                .customer(entity.getCustomer() != null ? customerMapper.toDomain(entity.getCustomer()) : null)
                .employee(entity.getEmployee() != null ? employeeMapper.toDomain(entity.getEmployee()) : null)
                .service(entity.getService() != null ? serviceMapper.toDomain(entity.getService()) : null)
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .status(mapStatusToDomain(entity.getStatus()))
                .notes(entity.getNotes())
                .totalPrice(entity.getTotalPrice())
                .companyId(entity.getCompanyId())
                .additionalServiceIds(entity.getAdditionalServiceIds())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
    
    /**
     * Converts a list of JPA AppointmentEntities to domain Appointments.
     *
     * @param entities the JPA entities
     * @return the domain appointments
     */
    public List<Appointment> toDomainList(List<AppointmentEntity> entities) {
        if (entities == null) {
            return List.of();
        }
        
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
    
    /**
     * Maps a status string to the AppointmentStatus enum.
     *
     * @param status the status string
     * @return the corresponding enum value, or REQUESTED if not recognized
     */
    private AppointmentStatus mapStatusToDomain(String status) {
        if (status == null) {
            return AppointmentStatus.REQUESTED;
        }
        
        try {
            return AppointmentStatus.valueOf(status);
        } catch (IllegalArgumentException e) {
            return AppointmentStatus.REQUESTED;
        }
    }
}