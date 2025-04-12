package com.beautycenter.management.domain.repository;

import com.beautycenter.management.domain.model.Company;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for Company domain entity.
 * This is part of the Hexagonal Architecture's port.
 */
public interface CompanyRepository {
    
    /**
     * Save a company.
     *
     * @param company the company to save
     * @return the saved company
     */
    Company save(Company company);
    
    /**
     * Find a company by ID.
     *
     * @param id the company ID as UUID
     * @return an Optional containing the company if found, empty otherwise
     */
    Optional<Company> findById(UUID id);
    
    /**
     * Find all companies.
     *
     * @return list of all companies
     */
    List<Company> findAll();
    
    /**
     * Find a company by name.
     *
     * @param name the company name
     * @return an Optional containing the company if found, empty otherwise
     */
    Optional<Company> findByName(String name);
    
    /**
     * Delete a company by ID.
     *
     * @param id the company ID as UUID
     */
    void deleteById(UUID id);
    
    /**
     * Check if a company exists by ID.
     *
     * @param id the company ID as UUID
     * @return true if exists, false otherwise
     */
    boolean existsById(UUID id);
    
    /**
     * Check if a company exists by name.
     *
     * @param name the company name
     * @return true if exists, false otherwise
     */
    boolean existsByName(String name);
    
    /**
     * Find companies by active status.
     *
     * @return list of active companies
     */
    List<Company> findByActiveTrue();
    
    /**
     * Find companies by name containing the given string.
     *
     * @param name the name string to search for
     * @return list of matching companies
     */
    List<Company> findByNameContaining(String name);
}