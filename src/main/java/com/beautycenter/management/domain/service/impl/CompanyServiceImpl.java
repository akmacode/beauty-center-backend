package com.beautycenter.management.domain.service.impl;

import com.beautycenter.management.application.dto.CompanyDTO;
import com.beautycenter.management.application.mapper.CompanyMapper;
import com.beautycenter.management.domain.model.Company;
import com.beautycenter.management.domain.repository.CompanyRepository;
import com.beautycenter.management.domain.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementation of the CompanyService interface.
 */
@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    
    @Override
    public CompanyDTO createCompany(CompanyDTO companyDTO) {
        Company company = companyMapper.toDomain(companyDTO);
        Company savedCompany = createCompany(company);
        return companyMapper.toDTO(savedCompany);
    }
    
    @Override
    public CompanyDTO getCompanyById(Long id) {
        // Convert Long to UUID for compatibility with the domain model
        UUID uuid = UUID.nameUUIDFromBytes(String.valueOf(id).getBytes());
        return findById(uuid)
                .map(companyMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Company not found with ID: " + id));
    }
    
    @Override
    public CompanyDTO getCompanyByName(String name) {
        return companyRepository.findByName(name)
                .map(companyMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Company not found with name: " + name));
    }
    
    @Override
    public List<CompanyDTO> getAllCompanies() {
        return findAll().stream()
                .map(companyMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public CompanyDTO updateCompany(Long id, CompanyDTO companyDTO) {
        // Convert Long to UUID for compatibility with the domain model
        UUID uuid = UUID.nameUUIDFromBytes(String.valueOf(id).getBytes());
        Company company = companyMapper.toDomain(companyDTO);
        Company updatedCompany = updateCompany(uuid, company);
        return companyMapper.toDTO(updatedCompany);
    }
    
    @Override
    public void deleteCompany(Long id) {
        // Convert Long to UUID for compatibility with the domain model
        UUID uuid = UUID.nameUUIDFromBytes(String.valueOf(id).getBytes());
        deleteCompany(uuid);
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
    public Optional<Company> findById(UUID id) {
        return companyRepository.findById(id);
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