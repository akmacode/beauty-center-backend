package com.beautycenter.management.domain.service;

import com.beautycenter.management.application.dto.ServiceDTO;

import java.util.List;
import java.util.Set;

/**
 * Service interface for Service operations.
 */
public interface ServiceService {
    
    /**
     * Create a new service.
     *
     * @param serviceDTO the service data
     * @return the created service
     */
    ServiceDTO createService(ServiceDTO serviceDTO);
    
    /**
     * Get service by ID.
     *
     * @param id the service ID
     * @return the service
     */
    ServiceDTO getServiceById(Long id);
    
    /**
     * Get all services.
     *
     * @return list of all services
     */
    List<ServiceDTO> getAllServices();
    
    /**
     * Get services by company ID.
     *
     * @param companyId the company ID
     * @return list of services for the company
     */
    List<ServiceDTO> getServicesByCompanyId(Long companyId);
    
    /**
     * Get active services by company ID.
     *
     * @param companyId the company ID
     * @return list of active services for the company
     */
    List<ServiceDTO> getActiveServicesByCompanyId(Long companyId);
    
    /**
     * Get services by IDs.
     *
     * @param ids the service IDs
     * @return list of services with the specified IDs
     */
    List<ServiceDTO> getServicesByIds(Set<Long> ids);
    
    /**
     * Get services by category.
     *
     * @param category the category
     * @return list of services in the specified category
     */
    List<ServiceDTO> getServicesByCategory(String category);
    
    /**
     * Get services by company ID and category.
     *
     * @param companyId the company ID
     * @param category the category
     * @return list of services for the company in the specified category
     */
    List<ServiceDTO> getServicesByCompanyIdAndCategory(Long companyId, String category);
    
    /**
     * Update service.
     *
     * @param id the service ID
     * @param serviceDTO the updated service data
     * @return the updated service
     */
    ServiceDTO updateService(Long id, ServiceDTO serviceDTO);
    
    /**
     * Change service active status.
     *
     * @param id the service ID
     * @param active the active status
     * @return the updated service
     */
    ServiceDTO changeServiceStatus(Long id, boolean active);
    
    /**
     * Delete service.
     *
     * @param id the service ID
     */
    void deleteService(Long id);
}