package com.beautycenter.management.domain.repository;

import com.beautycenter.management.domain.model.Company;

import java.util.List;
import java.util.Optional;

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
     * @param id the company ID
     * @return an Optional containing the company if found, empty otherwise
     */
    Optional<Company> findById(Long id);
    
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
     * @param id the company ID
     */
    void deleteById(Long id);
    
    /**
     * Check if a company exists by ID.
     *
     * @param id the company ID
     * @return true if exists, false otherwise
     */
    boolean existsById(Long id);
    
    /**
     * Check if a company exists by name.
     *
     * @param name the company name
     * @return true if exists, false otherwise
     */
    boolean existsByName(String name);
}