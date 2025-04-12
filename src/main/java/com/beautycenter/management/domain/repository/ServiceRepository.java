package com.beautycenter.management.domain.repository;

import com.beautycenter.management.domain.model.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Repository interface for Service domain entity.
 * This is part of the Hexagonal Architecture's port.
 */
public interface ServiceRepository {
    
    /**
     * Save a service.
     *
     * @param service the service to save
     * @return the saved service
     */
    Service save(Service service);
    
    /**
     * Find a service by ID.
     *
     * @param id the service ID
     * @return an Optional containing the service if found, empty otherwise
     */
    Optional<Service> findById(Long id);
    
    /**
     * Find all services.
     *
     * @return list of all services
     */
    List<Service> findAll();
    
    /**
     * Find services by company ID.
     *
     * @param companyId the company ID
     * @return list of services for the company
     */
    List<Service> findByCompanyId(Long companyId);
    
    /**
     * Find services by IDs.
     *
     * @param ids the set of service IDs
     * @return list of services with the specified IDs
     */
    List<Service> findByIdIn(Set<Long> ids);
    
    /**
     * Find active services by company ID.
     *
     * @param companyId the company ID
     * @return list of active services for the company
     */
    List<Service> findByCompanyIdAndActiveTrue(Long companyId);
    
    /**
     * Find services by category.
     *
     * @param category the service category
     * @return list of services in the specified category
     */
    List<Service> findByCategory(String category);
    
    /**
     * Find services by company ID and category.
     *
     * @param companyId the company ID
     * @param category the service category
     * @return list of services for the company in the specified category
     */
    List<Service> findByCompanyIdAndCategory(Long companyId, String category);
    
    /**
     * Delete a service by ID.
     *
     * @param id the service ID
     */
    void deleteById(Long id);
    
    /**
     * Check if a service exists by ID.
     *
     * @param id the service ID
     * @return true if exists, false otherwise
     */
    boolean existsById(Long id);
}