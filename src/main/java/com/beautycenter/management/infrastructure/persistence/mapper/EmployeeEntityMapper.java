package com.beautycenter.management.infrastructure.persistence.mapper;

import com.beautycenter.management.domain.model.Employee;
import com.beautycenter.management.infrastructure.persistence.entity.EmployeeEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Maps between Employee domain entities and JPA entities.
 * Provides a clean separation between domain and persistence layers.
 */
@Component
public class EmployeeEntityMapper {

    /**
     * Maps a domain Employee to a JPA EmployeeEntity.
     *
     * @param employee the domain employee
     * @return the JPA entity
     */
    public EmployeeEntity toEntity(Employee employee) {
        if (employee == null) {
            return null;
        }
        
        return EmployeeEntity.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .role(employee.getRole())
                .companyId(employee.getCompanyId())
                .createdAt(employee.getCreatedAt())
                .updatedAt(employee.getUpdatedAt())
                .build();
    }
    
    /**
     * Maps a JPA EmployeeEntity to a domain Employee.
     *
     * @param entity the JPA entity
     * @return the domain employee
     */
    public Employee toDomain(EmployeeEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return Employee.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .role(entity.getRole())
                .companyId(entity.getCompanyId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
    
    /**
     * Maps a list of JPA EmployeeEntities to domain Employees.
     *
     * @param entities the JPA entities
     * @return the domain employees
     */
    public List<Employee> toDomainList(List<EmployeeEntity> entities) {
        if (entities == null) {
            return List.of();
        }
        
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}