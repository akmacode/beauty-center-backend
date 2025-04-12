package com.beautycenter.management.application.service.impl;

import com.beautycenter.management.application.dto.CompanyDTO;
import com.beautycenter.management.application.mapper.CompanyMapper;
import com.beautycenter.management.application.service.CompanyApplicationService;
import com.beautycenter.management.domain.model.Company;
import com.beautycenter.management.domain.service.CompanyService;
import com.beautycenter.management.domain.service.exception.CompanyAlreadyExistsException;
import com.beautycenter.management.domain.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementation of the CompanyApplicationService that works with DTOs
 * and delegates to the domain service for domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CompanyApplicationServiceImpl implements CompanyApplicationService {
    
    @Qualifier("domainCompanyService")
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;
    
    /**
     * Create a new company.
     *
     * @param companyDTO the company data to create
     * @return the created company DTO
     */
    public CompanyDTO createCompany(CompanyDTO companyDTO) {
        // Map DTO to domain model
        Company company = companyMapper.toDomain(companyDTO);
        
        // Use domain service to create company
        Company savedCompany = companyService.createCompany(company);
        
        // Return as DTO
        return companyMapper.toDTO(savedCompany);
    }
    
    /**
     * Get a company by ID.
     *
     * @param id the company ID
     * @return the company DTO
     * @throws ResourceNotFoundException if company not found
     */
    @Transactional(readOnly = true)
    public CompanyDTO getCompanyById(Long id) {
        return companyService.findById(id)
                .map(companyMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + id));
    }
    
    /**
     * Get a company by name.
     *
     * @param name the company name
     * @return the company DTO
     * @throws ResourceNotFoundException if company not found
     */
    @Transactional(readOnly = true)
    public CompanyDTO getCompanyByName(String name) {
        return companyService.findByName(name)
                .map(companyMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with name: " + name));
    }
    
    /**
     * Get all companies.
     *
     * @return list of all company DTOs
     */
    @Transactional(readOnly = true)
    public List<CompanyDTO> getAllCompanies() {
        return companyService.findAll().stream()
                .map(companyMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Update a company.
     *
     * @param id the company ID to update
     * @param companyDTO the updated company data
     * @return the updated company DTO
     * @throws ResourceNotFoundException if company not found
     * @throws CompanyAlreadyExistsException if name already exists for another company
     */
    public CompanyDTO updateCompany(Long id, CompanyDTO companyDTO) {
        Company company = companyMapper.toDomain(companyDTO);
        Company updatedCompany = companyService.updateCompany(id, company);
        return companyMapper.toDTO(updatedCompany);
    }
    
    /**
     * Delete a company.
     *
     * @param id the company ID to delete
     * @throws ResourceNotFoundException if company not found
     */
    public void deleteCompany(Long id) {
        companyService.deleteCompany(id);
    }
    
    /**
     * Get all active companies.
     *
     * @return list of active company DTOs
     */
    @Transactional(readOnly = true)
    public List<CompanyDTO> getActiveCompanies() {
        return companyService.findActiveCompanies().stream()
                .map(companyMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Search companies by name.
     *
     * @param name the name string to search for
     * @return list of matching company DTOs
     */
    @Transactional(readOnly = true)
    public List<CompanyDTO> searchCompaniesByName(String name) {
        return companyService.findByNameContaining(name).stream()
                .map(companyMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Activate a company.
     *
     * @param id the company ID to activate
     * @return the activated company DTO
     * @throws ResourceNotFoundException if company not found
     */
    public CompanyDTO activateCompany(Long id) {
        // Convert Long to UUID for compatibility with the domain model
        UUID uuid = UUID.nameUUIDFromBytes(String.valueOf(id).getBytes());
        Company company = companyService.activateCompany(uuid);
        return companyMapper.toDTO(company);
    }
    
    /**
     * Deactivate a company.
     *
     * @param id the company ID to deactivate
     * @return the deactivated company DTO
     * @throws ResourceNotFoundException if company not found
     */
    public CompanyDTO deactivateCompany(Long id) {
        // Convert Long to UUID for compatibility with the domain model
        UUID uuid = UUID.nameUUIDFromBytes(String.valueOf(id).getBytes());
        Company company = companyService.deactivateCompany(uuid);
        return companyMapper.toDTO(company);
    }
}