package com.beautycenter.management.application.mapper;

import com.beautycenter.management.application.dto.CompanyDTO;
import com.beautycenter.management.domain.model.Company;
import org.springframework.stereotype.Component;

/**
 * Mapper between Company domain model and CompanyDTO.
 */
@Component
public class CompanyMapper {
    
    /**
     * Map Company domain model to CompanyDTO.
     *
     * @param domain the Company domain model
     * @return the CompanyDTO
     */
    public CompanyDTO toDTO(Company domain) {
        if (domain == null) {
            return null;
        }
        
        return CompanyDTO.builder()
//                .id(domain.getId())
//                .name(domain.getName())
//                .description(domain.getDescription())
//                .address(domain.getAddress())
//                .city(domain.getCity())
//                .state(domain.getState())
//                .zipCode(domain.getZipCode())
//                .country(domain.getCountry())
//                .phoneNumber(domain.getPhoneNumber())
//                .email(domain.getEmail())
//                .website(domain.getWebsite())
//                .logoUrl(domain.getLogoUrl())
//                .createdAt(domain.getCreatedAt())
//                .updatedAt(domain.getUpdatedAt())
                .build();
    }
    
    /**
     * Map CompanyDTO to Company domain model.
     *
     * @param dto the CompanyDTO
     * @return the Company domain model
     */
    public Company toDomain(CompanyDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return Company.builder()
//                .id(dto.getId())
//                .name(dto.getName())
//                .description(dto.getDescription())
//                .address(dto.getAddress())
//                .city(dto.getCity())
//                .state(dto.getState())
//                .zipCode(dto.getZipCode())
//                .country(dto.getCountry())
//                .phoneNumber(dto.getPhoneNumber())
//                .email(dto.getEmail())
//                .website(dto.getWebsite())
//                .logoUrl(dto.getLogoUrl())
//                .createdAt(dto.getCreatedAt())
//                .updatedAt(dto.getUpdatedAt())
                .build();
    }
}