package com.beautycenter.management.application.service;

import com.beautycenter.management.application.dto.CompanyDTO;

import java.util.List;

/**
 * Application service interface for Company operations.
 * This service works with DTOs and provides the interface for the application layer.
 */
public interface CompanyApplicationService {
    
    /**
     * Create a new company.
     *
     * @param companyDTO the company data to create
     * @return the created company DTO
     */
    CompanyDTO createCompany(CompanyDTO companyDTO);
    
    /**
     * Get a company by ID.
     *
     * @param id the company ID
     * @return the company DTO
     */
    CompanyDTO getCompanyById(Long id);
    
    /**
     * Get a company by name.
     *
     * @param name the company name
     * @return the company DTO
     */
    CompanyDTO getCompanyByName(String name);
    
    /**
     * Get all companies.
     *
     * @return list of all company DTOs
     */
    List<CompanyDTO> getAllCompanies();
    
    /**
     * Update a company.
     *
     * @param id the company ID to update
     * @param companyDTO the updated company data
     * @return the updated company DTO
     */
    CompanyDTO updateCompany(Long id, CompanyDTO companyDTO);
    
    /**
     * Delete a company.
     *
     * @param id the company ID to delete
     */
    void deleteCompany(Long id);
    
    /**
     * Get all active companies.
     *
     * @return list of active company DTOs
     */
    List<CompanyDTO> getActiveCompanies();
    
    /**
     * Search companies by name.
     *
     * @param name the name string to search for
     * @return list of matching company DTOs
     */
    List<CompanyDTO> searchCompaniesByName(String name);
    
    /**
     * Activate a company.
     *
     * @param id the company ID to activate
     * @return the activated company DTO
     */
    CompanyDTO activateCompany(Long id);
    
    /**
     * Deactivate a company.
     *
     * @param id the company ID to deactivate
     * @return the deactivated company DTO
     */
    CompanyDTO deactivateCompany(Long id);
}