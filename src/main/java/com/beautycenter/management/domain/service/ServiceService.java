package com.beautycenter.management.domain.service;

import com.beautycenter.management.domain.model.Service;

import java.util.List;
import java.util.Set;

/**
 * Service interface for Service operations in the domain layer.
 * This interface should only work with domain models, not DTOs.
 */
public interface ServiceService {
    
    /**
     * Create a new service.
     *
     * @param service the service data
     * @return the created service
     */
    Service createService(Service service);
    
    /**
     * Get service by ID.
     *
     * @param id the service ID
     * @return the service
     */
    Service getServiceById(Long id);
    
    /**
     * Get all services.
     *
     * @return list of all services
     */
    List<Service> getAllServices();
    
    /**
     * Get services by company ID.
     *
     * @param companyId the company ID
     * @return list of services for the company
     */
    List<Service> getServicesByCompanyId(Long companyId);
    
    /**
     * Get active services by company ID.
     *
     * @param companyId the company ID
     * @return list of active services for the company
     */
    List<Service> getActiveServicesByCompanyId(Long companyId);
    
    /**
     * Get services by IDs.
     *
     * @param ids the service IDs
     * @return list of services with the specified IDs
     */
    List<Service> getServicesByIds(Set<Long> ids);
    
    /**
     * Get services by category.
     *
     * @param category the category
     * @return list of services in the specified category
     */
    List<Service> getServicesByCategory(String category);
    
    /**
     * Get services by company ID and category.
     *
     * @param companyId the company ID
     * @param category the category
     * @return list of services for the company in the specified category
     */
    List<Service> getServicesByCompanyIdAndCategory(Long companyId, String category);
    
    /**
     * Update service.
     *
     * @param id the service ID
     * @param service the updated service data
     * @return the updated service
     */
    Service updateService(Long id, Service service);
    
    /**
     * Change service active status.
     *
     * @param id the service ID
     * @param active the active status
     * @return the updated service
     */
    Service changeServiceStatus(Long id, boolean active);
    
    /**
     * Delete service.
     *
     * @param id the service ID
     */
    void deleteService(Long id);
}