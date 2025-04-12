package com.beautycenter.management.infrastructure.persistence.repository;

import com.beautycenter.management.infrastructure.persistence.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Spring Data JPA repository for ServiceEntity.
 */
@Repository
public interface ServiceJpaRepository extends JpaRepository<ServiceEntity, Long> {
    
    /**
     * Find services by company ID.
     *
     * @param companyId the company ID
     * @return list of services for the company
     */
    List<ServiceEntity> findByCompanyId(Long companyId);
    
    /**
     * Find services by IDs.
     *
     * @param ids the set of service IDs
     * @return list of services with the specified IDs
     */
    List<ServiceEntity> findByIdIn(Set<Long> ids);
    
    /**
     * Find active services by company ID.
     *
     * @param companyId the company ID
     * @return list of active services for the company
     */
    List<ServiceEntity> findByCompanyIdAndActiveTrue(Long companyId);
    
    /**
     * Find services by category.
     *
     * @param category the service category
     * @return list of services in the specified category
     */
    List<ServiceEntity> findByCategory(String category);
    
    /**
     * Find services by company ID and category.
     *
     * @param companyId the company ID
     * @param category the service category
     * @return list of services for the company in the specified category
     */
    List<ServiceEntity> findByCompanyIdAndCategory(Long companyId, String category);
}