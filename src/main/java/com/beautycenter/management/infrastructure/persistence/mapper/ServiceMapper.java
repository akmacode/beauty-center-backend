package com.beautycenter.management.infrastructure.persistence.mapper;

import com.beautycenter.management.domain.model.Service;
import com.beautycenter.management.infrastructure.persistence.entity.ServiceEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper between Service domain model and ServiceEntity.
 */
@Component
public class ServiceMapper {
    
    /**
     * Map ServiceEntity to Service domain model.
     *
     * @param entity the ServiceEntity
     * @return the Service domain model
     */
    public Service toDomain(ServiceEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return Service.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .durationMinutes(entity.getDurationMinutes())
                .category(entity.getCategory())
                .imageUrl(entity.getImageUrl())
                .active(entity.isActive())
                .companyId(entity.getCompanyId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
    
    /**
     * Map Service domain model to ServiceEntity.
     *
     * @param domain the Service domain model
     * @return the ServiceEntity
     */
    public ServiceEntity toEntity(Service domain) {
        if (domain == null) {
            return null;
        }
        
        return ServiceEntity.builder()
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
}