package com.beautycenter.management.application.mapper;

import com.beautycenter.management.application.dto.ServiceDTO;
import com.beautycenter.management.domain.model.Service;
import org.springframework.stereotype.Component;

/**
 * Mapper between Service domain model and ServiceDTO.
 */
@Component
public class ServiceMapper {
    
    /**
     * Map Service domain model to ServiceDTO.
     *
     * @param domain the Service domain model
     * @return the ServiceDTO
     */
    public ServiceDTO toDTO(Service domain) {
        if (domain == null) {
            return null;
        }
        
        return ServiceDTO.builder()
                .id(domain.getId())
                .name(domain.getName())
                .description(domain.getDescription())
                .price(domain.getPrice())
                .durationMinutes(domain.getDurationMinutes())
                .category(domain.getCategory())
                .imageUrl(domain.getImageUrl())
                .active(domain.isActive())
                .companyId(domain.getCompanyId())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }
    
    /**
     * Map ServiceDTO to Service domain model.
     *
     * @param dto the ServiceDTO
     * @return the Service domain model
     */
    public Service toDomain(ServiceDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return Service.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .durationMinutes(dto.getDurationMinutes())
                .category(dto.getCategory())
                .imageUrl(dto.getImageUrl())
                .active(dto.isActive())
                .companyId(dto.getCompanyId())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }
}