package com.beautycenter.management.infrastructure.persistence.mapper;

import com.beautycenter.management.domain.model.Appointment;
import com.beautycenter.management.infrastructure.persistence.entity.AppointmentEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper between Appointment domain model and AppointmentEntity.
 */
@Component
public class AppointmentMapper {
    
    /**
     * Map AppointmentEntity to Appointment domain model.
     *
     * @param entity the AppointmentEntity
     * @return the Appointment domain model
     */
    public Appointment toDomain(AppointmentEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return Appointment.builder()
                .id(entity.getId())
                .customerId(entity.getCustomerId())
                .employeeId(entity.getEmployeeId())
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .notes(entity.getNotes())
                .status(entity.getStatus())
                .serviceIds(entity.getServiceIds())
                .totalPrice(entity.getTotalPrice())
                .companyId(entity.getCompanyId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
    
    /**
     * Map Appointment domain model to AppointmentEntity.
     *
     * @param domain the Appointment domain model
     * @return the AppointmentEntity
     */
    public AppointmentEntity toEntity(Appointment domain) {
        if (domain == null) {
            return null;
        }
        
        return AppointmentEntity.builder()
                .id(domain.getId())
                .customerId(domain.getCustomerId())
                .employeeId(domain.getEmployeeId())
                .startTime(domain.getStartTime())
                .endTime(domain.getEndTime())
                .notes(domain.getNotes())
                .status(domain.getStatus())
                .serviceIds(domain.getServiceIds())
                .totalPrice(domain.getTotalPrice())
                .companyId(domain.getCompanyId())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }
}