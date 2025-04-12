package com.beautycenter.management.domain.service;

import com.beautycenter.management.domain.model.Company;
import com.beautycenter.management.domain.model.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for beauty service-related business logic.
 * This is part of the domain layer in DDD and provides the application with access to domain logic.
 * Note: Named BeautyServiceService to avoid naming collision with java.util.Service
 */
public interface BeautyServiceService {
    
    /**
     * Create a new service
     */
    Service createService(Service service);
    
    /**
     * Find a service by ID
     */
    Optional<Service> findById(Long id);
    
    /**
     * Find all services
     */
    List<Service> findAllServices();
    
    /**
     * Find services by company
     */
    List<Service> findServicesByCompany(Company company);
    
    /**
     * Find active services by company
     */
    List<Service> findActiveServicesByCompany(Company company);
    
    /**
     * Find services by category
     */
    List<Service> findServicesByCategory(String category);
    
    /**
     * Update a service
     */
    Service updateService(Service service);
    
    /**
     * Delete a service
     */
    void deleteService(Long id);
    
    /**
     * Activate a service
     */
    Service activateService(Long id);
    
    /**
     * Deactivate a service
     */
    Service deactivateService(Long id);
}