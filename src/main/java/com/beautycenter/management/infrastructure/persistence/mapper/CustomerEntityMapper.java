package com.beautycenter.management.infrastructure.persistence.mapper;

import com.beautycenter.management.domain.model.Customer;
import com.beautycenter.management.infrastructure.persistence.entity.CustomerEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Maps between Customer domain entities and JPA entities.
 * Provides a clean separation between domain and persistence layers.
 */
@Component
public class CustomerEntityMapper {

    /**
     * Maps a domain Customer to a JPA CustomerEntity.
     *
     * @param customer the domain customer
     * @return the JPA entity
     */
    public CustomerEntity toEntity(Customer customer) {
        if (customer == null) {
            return null;
        }
        
        return CustomerEntity.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .address(customer.getAddress())
                .createdAt(customer.getCreatedAt())
                .updatedAt(customer.getUpdatedAt())
                .build();
    }
    
    /**
     * Maps a JPA CustomerEntity to a domain Customer.
     *
     * @param entity the JPA entity
     * @return the domain customer
     */
    public Customer toDomain(CustomerEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return Customer.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .address(entity.getAddress())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
    
    /**
     * Maps a list of JPA CustomerEntities to domain Customers.
     *
     * @param entities the JPA entities
     * @return the domain customers
     */
    public List<Customer> toDomainList(List<CustomerEntity> entities) {
        if (entities == null) {
            return List.of();
        }
        
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}