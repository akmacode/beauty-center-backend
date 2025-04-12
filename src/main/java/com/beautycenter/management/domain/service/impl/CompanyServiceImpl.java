package com.beautycenter.management.domain.service.impl;

import com.beautycenter.management.domain.model.Company;
import com.beautycenter.management.domain.repository.CompanyRepository;
import com.beautycenter.management.domain.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Domain layer implementation of the CompanyService interface.
 * This implementation works only with domain models, not DTOs.
 */
@Service("domainCompanyService")
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    
    private final CompanyRepository companyRepository;
    
    @Override
    public Optional<Company> findById(UUID id) {
        return companyRepository.findById(id);
    }
    
    @Override
    public Optional<Company> findById(Long id) {
        // Convert Long to UUID for compatibility with the domain model
        UUID uuid = UUID.nameUUIDFromBytes(String.valueOf(id).getBytes());
        return companyRepository.findById(uuid);
    }
    
    @Override
    public Optional<Company> findByName(String name) {
        return companyRepository.findByName(name);
    }
    
    @Override
    public Company createCompany(Company company) {
        validateCompany(company);
        
        if (company.getId() == null) {
            company.setId(UUID.randomUUID());
        }
        
        company.setActive(true);
        
        // Set creation time if not set
        if (company.getCreatedAt() == null) {
            company.setCreatedAt(LocalDateTime.now());
        }
        company.setUpdatedAt(LocalDateTime.now());
        
        return companyRepository.save(company);
    }
    
    @Override
    public Company updateCompany(UUID id, Company company) {
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Company not found with ID: " + id));
        
        // Preserve the original ID
        company.setId(existingCompany.getId());
        
        validateCompany(company);
        
        // Preserve creation time
        company.setCreatedAt(existingCompany.getCreatedAt());
        company.setUpdatedAt(LocalDateTime.now());
        
        return companyRepository.save(company);
    }
    
    @Override
    public Company updateCompany(Long id, Company company) {
        // Convert Long to UUID for compatibility with the domain model
        UUID uuid = UUID.nameUUIDFromBytes(String.valueOf(id).getBytes());
        return updateCompany(uuid, company);
    }
    
    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }
    
    @Override
    public void deleteCompany(UUID id) {
        if (!companyRepository.existsById(id)) {
            throw new IllegalArgumentException("Company not found with ID: " + id);
        }
        companyRepository.deleteById(id);
    }
    
    @Override
    public void deleteCompany(Long id) {
        // Convert Long to UUID for compatibility with the domain model
        UUID uuid = UUID.nameUUIDFromBytes(String.valueOf(id).getBytes());
        deleteCompany(uuid);
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
        company.setUpdatedAt(LocalDateTime.now());
        return companyRepository.save(company);
    }
    
    @Override
    public Company activateCompany(UUID id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Company not found with ID: " + id));
        
        company.setActive(true);
        company.setUpdatedAt(LocalDateTime.now());
        return companyRepository.save(company);
    }
    
    /**
     * Validates that required company data is present.
     * 
     * @param company the company to validate
     * @throws IllegalArgumentException if validation fails
     */
    private void validateCompany(Company company) {
        if (company == null) {
            throw new IllegalArgumentException("Company cannot be null");
        }
        
        if (company.getName() == null || company.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Company name is required");
        }
    }
}