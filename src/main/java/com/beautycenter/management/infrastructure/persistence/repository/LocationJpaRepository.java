package com.beautycenter.management.infrastructure.persistence.repository;

import com.beautycenter.management.infrastructure.persistence.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for LocationEntity.
 */
@Repository
public interface LocationJpaRepository extends JpaRepository<LocationEntity, Long> {
    
    /**
     * Find locations by company ID.
     *
     * @param companyId the company ID
     * @return list of locations for the company
     */
    List<LocationEntity> findByCompanyId(Long companyId);
    
    /**
     * Find active locations by company ID.
     *
     * @param companyId the company ID
     * @return list of active locations for the company
     */
    List<LocationEntity> findByCompanyIdAndActiveTrue(Long companyId);
    
    /**
     * Find a location by company ID and name.
     *
     * @param companyId the company ID
     * @param name the location name
     * @return an Optional containing the location if found, empty otherwise
     */
    Optional<LocationEntity> findByCompanyIdAndName(Long companyId, String name);
    
    /**
     * Find locations by city.
     *
     * @param city the city
     * @return list of locations in the specified city
     */
    List<LocationEntity> findByCity(String city);
    
    /**
     * Find locations by state.
     *
     * @param state the state
     * @return list of locations in the specified state
     */
    List<LocationEntity> findByState(String state);
    
    /**
     * Find locations by city and state.
     *
     * @param city the city
     * @param state the state
     * @return list of locations in the specified city and state
     */
    List<LocationEntity> findByCityAndState(String city, String state);
    
    /**
     * Check if a location exists by company ID and name.
     *
     * @param companyId the company ID
     * @param name the location name
     * @return true if exists, false otherwise
     */
    boolean existsByCompanyIdAndName(Long companyId, String name);
}