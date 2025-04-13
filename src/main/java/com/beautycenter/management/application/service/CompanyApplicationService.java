package com.beautycenter.management.application.service;

import com.beautycenter.management.domain.model.Company;
import com.beautycenter.management.domain.service.CompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Application service for Company entity.
 * Acts as an adapter between the domain layer and the interface layer.
 */
@Service
@Transactional
public class CompanyApplicationService {
    
    private final CompanyService companyService;
    
    /**
     * Constructor with dependencies
     * 
     * @param companyService the company service
     */
    public CompanyApplicationService(CompanyService companyService) {
        this.companyService = companyService;
    }
    
    /**
     * Create a new company.
     *
     * @param company the company to create
     * @return the created company
     */
    public Company createCompany(Company company) {
        return companyService.createCompany(company);
    }
    
    /**
     * Find a company by its ID.
     *
     * @param id the company ID
     * @return the company if found
     */
    @Transactional(readOnly = true)
    public Optional<Company> findById(UUID id) {
        return companyService.findById(id);
    }
    
    /**
     * Update a company.
     *
     * @param id the company ID to update
     * @param company the updated company data
     * @return the updated company
     */
    public Company updateCompany(UUID id, Company company) {
        return companyService.updateCompany(id, company);
    }
    
    /**
     * Find all companies.
     *
     * @return list of all companies
     */
    @Transactional(readOnly = true)
    public List<Company> findAll() {
        return companyService.findAll();
    }
    
    /**
     * Delete a company.
     *
     * @param id the company ID to delete
     */
    public void deleteCompany(UUID id) {
        companyService.deleteCompany(id);
    }
    
    /**
     * Find companies by name containing the given string.
     *
     * @param name the name string to search for
     * @return list of matching companies
     */
    @Transactional(readOnly = true)
    public List<Company> findByNameContaining(String name) {
        return companyService.findByNameContaining(name);
    }
    
    /**
     * Find active companies.
     *
     * @return list of active companies
     */
    @Transactional(readOnly = true)
    public List<Company> findActiveCompanies() {
        return companyService.findActiveCompanies();
    }
    
    /**
     * Deactivate a company.
     *
     * @param id the company ID to deactivate
     * @return the deactivated company
     */
    public Company deactivateCompany(UUID id) {
        return companyService.deactivateCompany(id);
    }
    
    /**
     * Activate a company.
     *
     * @param id the company ID to activate
     * @return the activated company
     */
    public Company activateCompany(UUID id) {
        return companyService.activateCompany(id);
    }
}