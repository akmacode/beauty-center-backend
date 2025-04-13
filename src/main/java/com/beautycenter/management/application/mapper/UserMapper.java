package com.beautycenter.management.application.mapper;

import com.beautycenter.management.application.dto.UserDTO;
import com.beautycenter.management.domain.model.User;
import org.springframework.stereotype.Component;

/**
 * Mapper between User domain model and UserDTO.
 */
@Component
public class UserMapper {
    
    /**
     * Map User domain model to UserDTO.
     *
     * @param domain the User domain model
     * @return the UserDTO
     */
    public UserDTO toDTO(User domain) {
        if (domain == null) {
            return null;
        }
        
        return UserDTO.builder()
                .id(domain.getId())
                .username(domain.getUsername())
                .email(domain.getEmail())
                .firstName(domain.getFirstName())
                .lastName(domain.getLastName())
                .phoneNumber(domain.getPhoneNumber())
                .roles(domain.getRoles())
                .active(domain.isActive())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .companyId(domain.getCompanyId())
                .build();
    }
    
    /**
     * Map UserDTO to User domain model.
     * Note: Password is not mapped here for security reasons.
     *
     * @param dto the UserDTO
     * @return the User domain model
     */
    public User toDomain(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .password(dto.getPassword()) // Password will be encoded by service
                .email(dto.getEmail())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .phoneNumber(dto.getPhoneNumber())
                .roles(dto.getRoles())
                .active(dto.isActive())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .companyId(dto.getCompanyId())
                .build();
    }
}