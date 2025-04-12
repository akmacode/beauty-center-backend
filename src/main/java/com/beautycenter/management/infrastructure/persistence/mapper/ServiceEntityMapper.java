package com.beautycenter.management.infrastructure.persistence.mapper;

import com.beautycenter.management.domain.model.Service;
import com.beautycenter.management.infrastructure.persistence.entity.ServiceEntity;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Maps between Service domain entities and JPA entities.
 * Provides a clean separation between domain and persistence layers.
 */
@Component
public class ServiceEntityMapper {

    /**
     * Maps a domain Service to a JPA ServiceEntity.
     *
     * @param service the domain service
     * @return the JPA entity
     */
    public ServiceEntity toEntity(Service service) {
        if (service == null) {
            return null;
        }
        
        return ServiceEntity.builder()
                .id(service.getId())
                .name(service.getName())
                .description(service.getDescription())
                .durationMinutes(service.getDurationMinutes())
                .price(service.getPrice())
                .companyId(service.getCompanyId())
                .category(service.getCategory())
                .active(service.isActive())
                .createdAt(service.getCreatedAt())
                .updatedAt(service.getUpdatedAt())
                .build();
    }
    
    /**
     * Maps a JPA ServiceEntity to a domain Service.
     *
     * @param entity the JPA entity
     * @return the domain service
     */
    public Service toDomain(ServiceEntity entity) {
        if (entity == null) {
            return null;
        }
        
        Service service = Service.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .companyId(entity.getCompanyId())
                .category(entity.getCategory())
                .active(entity.isActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
        
        if (entity.getDurationMinutes() != null) {
            service.setDuration(Duration.ofMinutes(entity.getDurationMinutes()));
        }
        
        return service;
    }
    
    /**
     * Maps a list of JPA ServiceEntities to domain Services.
     *
     * @param entities the JPA entities
     * @return the domain services
     */
    public List<Service> toDomainList(List<ServiceEntity> entities) {
        if (entities == null) {
            return List.of();
        }
        
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}