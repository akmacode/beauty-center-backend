package com.beautycenter.management.domain.service;

import com.beautycenter.management.application.dto.CompanyDTO;
import com.beautycenter.management.domain.model.Company;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service interface for Company operations.
 */
public interface CompanyService {
    
    /**
     * Create a new company.
     *
     * @param companyDTO the company data
     * @return the created company
     */
    CompanyDTO createCompany(CompanyDTO companyDTO);
    
    /**
     * Get company by ID.
     *
     * @param id the company ID
     * @return the company
     */
    CompanyDTO getCompanyById(Long id);
    
    /**
     * Get company by name.
     *
     * @param name the company name
     * @return the company
     */
    CompanyDTO getCompanyByName(String name);
    
    /**
     * Get all companies.
     *
     * @return list of all companies
     */
    List<CompanyDTO> getAllCompanies();
    
    /**
     * Update company.
     *
     * @param id the company ID
     * @param companyDTO the updated company data
     * @return the updated company
     */
    CompanyDTO updateCompany(Long id, CompanyDTO companyDTO);
    
    /**
     * Delete company.
     *
     * @param id the company ID
     */
    void deleteCompany(Long id);
    
    /**
     * Find active companies.
     *
     * @return list of active companies
     */
    List<Company> findActiveCompanies();
    
    /**
     * Find company by ID.
     *
     * @param id the company ID
     * @return the company if found
     */
    Optional<Company> findById(UUID id);
    
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
     * Delete a company by ID.
     *
     * @param id the company ID to delete
     */
    void deleteCompany(UUID id);
    
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