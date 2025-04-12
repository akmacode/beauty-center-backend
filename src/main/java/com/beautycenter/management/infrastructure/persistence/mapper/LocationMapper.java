package com.beautycenter.management.infrastructure.persistence.mapper;

import com.beautycenter.management.domain.model.Location;
import com.beautycenter.management.infrastructure.persistence.entity.LocationEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper between Location domain model and LocationEntity.
 */
@Component
public class LocationMapper {
    
    /**
     * Map LocationEntity to Location domain model.
     *
     * @param entity the LocationEntity
     * @return the Location domain model
     */
    public Location toDomain(LocationEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return Location.builder()
                .id(entity.getId())
                .companyId(entity.getCompanyId())
                .name(entity.getName())
                .address(entity.getAddress())
                .city(entity.getCity())
                .state(entity.getState())
                .zipCode(entity.getZipCode())
                .country(entity.getCountry())
                .phoneNumber(entity.getPhoneNumber())
                .email(entity.getEmail())
                .openingHours(entity.getOpeningHours())
                .active(entity.isActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
    
    /**
     * Map Location domain model to LocationEntity.
     *
     * @param domain the Location domain model
     * @return the LocationEntity
     */
    public LocationEntity toEntity(Location domain) {
        if (domain == null) {
            return null;
        }
        
        return LocationEntity.builder()
                .id(domain.getId())
                .companyId(domain.getCompanyId())
                .name(domain.getName())
                .address(domain.getAddress())
                .city(domain.getCity())
                .state(domain.getState())
                .zipCode(domain.getZipCode())
                .country(domain.getCountry())
                .phoneNumber(domain.getPhoneNumber())
                .email(domain.getEmail())
                .openingHours(domain.getOpeningHours())
                .active(domain.isActive())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }
}