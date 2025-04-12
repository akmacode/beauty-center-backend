package com.beautycenter.management.domain.service;

import com.beautycenter.management.application.dto.CompanyDTO;

import java.util.List;

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
}