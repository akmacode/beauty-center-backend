package com.beautycenter.management.domain.service;

import com.beautycenter.management.domain.model.Company;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service interface for Company operations in the domain layer.
 * This interface should only work with domain models, not DTOs.
 */
public interface CompanyService {
    
    /**
     * Find active companies.
     *
     * @return list of active companies
     */
    List<Company> findActiveCompanies();
    
    /**
     * Find company by ID (UUID).
     *
     * @param id the company ID as UUID
     * @return the company if found
     */
    Optional<Company> findById(UUID id);
    
    /**
     * Find company by ID (Long).
     *
     * @param id the company ID as Long
     * @return the company if found
     */
    Optional<Company> findById(Long id);
    
    /**
     * Find company by name.
     *
     * @param name the company name
     * @return the company if found
     */
    Optional<Company> findByName(String name);
    
    /**
     * Find all companies.
     *
     * @return list of all companies
     */
    List<Company> findAll();
    
    /**
     * Create a new company.
     *
     * @param company the company to create
     * @return the created company
     */
    Company createCompany(Company company);
    
    /**
     * Update a company.
     *
     * @param id the company ID to update
     * @param company the updated company data
     * @return the updated company
     */
    Company updateCompany(UUID id, Company company);
    
    /**
     * Update a company.
     *
     * @param id the company ID to update (as Long)
     * @param company the updated company data
     * @return the updated company
     */
    Company updateCompany(Long id, Company company);
    
    /**
     * Delete a company by ID (UUID).
     *
     * @param id the company ID to delete
     */
    void deleteCompany(UUID id);
    
    /**
     * Delete a company by ID (Long).
     *
     * @param id the company ID to delete
     */
    void deleteCompany(Long id);
    
    /**
     * Find companies by name containing the given string.
     *
     * @param name the name string to search for
     * @return list of matching companies
     */
    List<Company> findByNameContaining(String name);
    
    /**
     * Deactivate a company.
     *
     * @param id the company ID to deactivate
     * @return the deactivated company
     */
    Company deactivateCompany(UUID id);
    
    /**
     * Activate a company.
     *
     * @param id the company ID to activate
     * @return the activated company
     */
    Company activateCompany(UUID id);
}