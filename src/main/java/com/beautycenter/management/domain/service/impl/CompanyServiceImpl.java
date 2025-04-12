package com.beautycenter.management.domain.service.impl;

import com.beautycenter.management.domain.model.Company;
import com.beautycenter.management.domain.repository.CompanyRepository;
import com.beautycenter.management.domain.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of the CompanyService interface.
 */
@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    
    private final CompanyRepository companyRepository;
    
    @Override
    public Company createCompany(Company company) {
        if (!company.isValid()) {
            throw new IllegalArgumentException("Invalid company data");
        }
        
        if (company.getId() == null) {
            company.setId(UUID.randomUUID());
        }
        
        company.setActive(true);
        
        return companyRepository.save(company);
    }
    
    @Override
    public Optional<Company> findById(UUID id) {
        return companyRepository.findById(id);
    }
    
    @Override
    public Company updateCompany(UUID id, Company company) {
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Company not found with ID: " + id));
        
        // Preserve the original ID
        company.setId(existingCompany.getId());
        
        if (!company.isValid()) {
            throw new IllegalArgumentException("Invalid company data");
        }
        
        // Preserve the locations from the existing company to avoid losing that relationship
        company.setLocations(existingCompany.getLocations());
        
        return companyRepository.save(company);
    }
    
    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }
    
    @Override
    public void deleteCompany(UUID id) {
        if (!companyRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("Company not found with ID: " + id);
        }
        companyRepository.deleteById(id);
    }
    
    @Override
    public List<Company> findByNameContaining(String name) {
        return companyRepository.findByNameContaining(name);
    }
    
    @Override
    public List<Company> findActiveCompanies() {
        return companyRepository.findByActiveTrue();
    }
    
    @Override
    public Company deactivateCompany(UUID id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Company not found with ID: " + id));
        
        company.setActive(false);
        return companyRepository.save(company);
    }
    
    @Override
    public Company activateCompany(UUID id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Company not found with ID: " + id));
        
        company.setActive(true);
        return companyRepository.save(company);
    }
}