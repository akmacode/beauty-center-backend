package com.beautycenter.management.application.mapper;

import com.beautycenter.management.application.dto.AppointmentDto;
import com.beautycenter.management.domain.model.Appointment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper class that converts between Appointment domain entities and DTOs.
 * This provides a clean separation between the domain layer and application layer.
 */
@Component
public class AppointmentDtoMapper {

    /**
     * Maps a domain Appointment to an AppointmentDto.
     *
     * @param appointment the domain appointment entity
     * @return the corresponding DTO
     */
    public AppointmentDto toDto(Appointment appointment) {
        if (appointment == null) {
            return null;
        }
        
        return AppointmentDto.builder()
                .id(appointment.getId())
                .customerId(appointment.getCustomer() != null ? appointment.getCustomer().getId() : null)
                .customerName(appointment.getCustomer() != null ? appointment.getCustomer().getFullName() : null)
                .employeeId(appointment.getEmployee() != null ? appointment.getEmployee().getId() : null)
                .employeeName(appointment.getEmployee() != null ? appointment.getEmployee().getFullName() : null)
                .serviceId(appointment.getService() != null ? appointment.getService().getId() : null)
                .serviceName(appointment.getService() != null ? appointment.getService().getName() : null)
                .startTime(appointment.getStartTime())
                .endTime(appointment.getEndTime())
                .status(appointment.getStatus().toString())
                .notes(appointment.getNotes())
                .totalPrice(appointment.getTotalPrice())
                .companyId(appointment.getCompanyId())
                .additionalServiceIds(appointment.getAdditionalServiceIds())
                .build();
    }
    
    /**
     * Maps a list of domain Appointments to a list of AppointmentDtos.
     *
     * @param appointments the domain appointment entities
     * @return the corresponding DTOs
     */
    public List<AppointmentDto> toDtoList(List<Appointment> appointments) {
        if (appointments == null) {
            return List.of();
        }
        
        return appointments.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    
    /**
     * Maps an AppointmentDto to a domain Appointment.
     * Note: This performs a partial mapping and may need to be supplemented with
     * additional data like full Customer, Employee, and Service objects.
     *
     * @param dto the appointment DTO
     * @return the corresponding domain entity
     */
    public Appointment toDomain(AppointmentDto dto) {
        if (dto == null) {
            return null;
        }
        
        return Appointment.builder()
                .id(dto.getId())
                // Note: Customer, Employee, and Service objects will need to be populated separately
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .notes(dto.getNotes())
                .status(mapStatus(dto.getStatus()))
                .totalPrice(dto.getTotalPrice())
                .companyId(dto.getCompanyId())
                .additionalServiceIds(dto.getAdditionalServiceIds())
                .build();
    }
    
    /**
     * Maps a status string to the corresponding AppointmentStatus enum value.
     * 
     * @param status the status string
     * @return the corresponding enum value, or REQUESTED if not recognized
     */
    private com.beautycenter.management.domain.model.AppointmentStatus mapStatus(String status) {
        if (status == null) {
            return com.beautycenter.management.domain.model.AppointmentStatus.REQUESTED;
        }
        
        try {
            return com.beautycenter.management.domain.model.AppointmentStatus.valueOf(status);
        } catch (IllegalArgumentException e) {
            return com.beautycenter.management.domain.model.AppointmentStatus.REQUESTED;
        }
    }
}