package com.beautycenter.management.infrastructure.persistence.mapper;

import com.beautycenter.management.domain.model.User;
import com.beautycenter.management.infrastructure.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper between User domain model and UserEntity.
 */
@Component
public class UserMapper {
    
    /**
     * Map UserEntity to User domain model.
     *
     * @param entity the UserEntity
     * @return the User domain model
     */
    public User toDomain(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return User.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .phoneNumber(entity.getPhoneNumber())
                .roles(entity.getRoles())
                .active(entity.isActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .companyId(entity.getCompanyId())
                .build();
    }
    
    /**
     * Map User domain model to UserEntity.
     *
     * @param domain the User domain model
     * @return the UserEntity
     */
    public UserEntity toEntity(User domain) {
        if (domain == null) {
            return null;
        }
        
        return UserEntity.builder()
                .id(domain.getId())
                .username(domain.getUsername())
                .password(domain.getPassword())
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
}