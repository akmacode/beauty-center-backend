package com.beautycenter.management.infrastructure.persistence.mapper;

import com.beautycenter.management.domain.model.Company;
import com.beautycenter.management.infrastructure.persistence.entity.CompanyEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper between Company domain model and CompanyEntity.
 */
@Component
public class CompanyMapper {
    
    /**
     * Map CompanyEntity to Company domain model.
     *
     * @param entity the CompanyEntity
     * @return the Company domain model
     */
    public Company toDomain(CompanyEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return Company.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .address(entity.getAddress())
                .city(entity.getCity())
                .state(entity.getState())
                .zipCode(entity.getZipCode())
                .country(entity.getCountry())
                .phoneNumber(entity.getPhoneNumber())
                .email(entity.getEmail())
                .website(entity.getWebsite())
                .logoUrl(entity.getLogoUrl())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
    
    /**
     * Map Company domain model to CompanyEntity.
     *
     * @param domain the Company domain model
     * @return the CompanyEntity
     */
    public CompanyEntity toEntity(Company domain) {
        if (domain == null) {
            return null;
        }
        
        return CompanyEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .description(domain.getDescription())
                .address(domain.getAddress())
                .city(domain.getCity())
                .state(domain.getState())
                .zipCode(domain.getZipCode())
                .country(domain.getCountry())
                .phoneNumber(domain.getPhoneNumber())
                .email(domain.getEmail())
                .website(domain.getWebsite())
                .logoUrl(domain.getLogoUrl())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }
}