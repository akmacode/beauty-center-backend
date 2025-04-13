package com.beautycenter.management.domain.repository;

import com.beautycenter.management.domain.model.Location;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Location domain entity.
 * This is part of the Hexagonal Architecture's port.
 */
public interface LocationRepository {
    
    /**
     * Save a location.
     *
     * @param location the location to save
     * @return the saved location
     */
    Location save(Location location);
    
    /**
     * Find a location by ID.
     *
     * @param id the location ID
     * @return an Optional containing the location if found, empty otherwise
     */
    Optional<Location> findById(Long id);
    
    /**
     * Find all locations.
     *
     * @return list of all locations
     */
    List<Location> findAll();
    
    /**
     * Find locations by company ID.
     *
     * @param companyId the company ID
     * @return list of locations for the company
     */
    List<Location> findByCompanyId(Long companyId);
    
    /**
     * Find active locations by company ID.
     *
     * @param companyId the company ID
     * @return list of active locations for the company
     */
    List<Location> findByCompanyIdAndActiveTrue(Long companyId);
    
    /**
     * Find a location by company ID and name.
     *
     * @param companyId the company ID
     * @param name the location name
     * @return an Optional containing the location if found, empty otherwise
     */
    Optional<Location> findByCompanyIdAndName(Long companyId, String name);
    
    /**
     * Find locations by city.
     *
     * @param city the city
     * @return list of locations in the specified city
     */
    List<Location> findByCity(String city);
    
    /**
     * Find locations by state.
     *
     * @param state the state
     * @return list of locations in the specified state
     */
    List<Location> findByState(String state);
    
    /**
     * Find locations by city and state.
     *
     * @param city the city
     * @param state the state
     * @return list of locations in the specified city and state
     */
    List<Location> findByCityAndState(String city, String state);
    
    /**
     * Delete a location by ID.
     *
     * @param id the location ID
     */
    void deleteById(Long id);
    
    /**
     * Check if a location exists by ID.
     *
     * @param id the location ID
     * @return true if exists, false otherwise
     */
    boolean existsById(Long id);
    
    /**
     * Check if a location exists by company ID and name.
     *
     * @param companyId the company ID
     * @param name the location name
     * @return true if exists, false otherwise
     */
    boolean existsByCompanyIdAndName(Long companyId, String name);
}