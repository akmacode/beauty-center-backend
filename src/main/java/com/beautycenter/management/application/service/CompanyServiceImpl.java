package com.beautycenter.management.application.service;

import com.beautycenter.management.application.dto.CompanyDTO;
import com.beautycenter.management.application.mapper.CompanyMapper;
import com.beautycenter.management.domain.model.Company;
import com.beautycenter.management.domain.repository.CompanyRepository;
import com.beautycenter.management.domain.service.CompanyService;
import com.beautycenter.management.domain.service.exception.CompanyAlreadyExistsException;
import com.beautycenter.management.domain.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the CompanyService.
 */
@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    
    @Override
    @Transactional
    public CompanyDTO createCompany(CompanyDTO companyDTO) {
        // Check if company name already exists
        if (companyRepository.existsByName(companyDTO.getName())) {
            throw new CompanyAlreadyExistsException("Company already exists with name: " + companyDTO.getName());
        }
        
        // Map DTO to domain model
        Company company = companyMapper.toDomain(companyDTO);
        
        // Set additional properties
        company.setCreatedAt(LocalDateTime.now());
        company.setUpdatedAt(LocalDateTime.now());
        
        // Save company
        Company savedCompany = companyRepository.save(company);
        
        // Return as DTO
        return companyMapper.toDTO(savedCompany);
    }
    
    @Override
    @Transactional(readOnly = true)
    public CompanyDTO getCompanyById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + id));
        
        return companyMapper.toDTO(company);
    }
    
    @Override
    @Transactional(readOnly = true)
    public CompanyDTO getCompanyByName(String name) {
        Company company = companyRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with name: " + name));
        
        return companyMapper.toDTO(company);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<CompanyDTO> getAllCompanies() {
        return companyRepository.findAll().stream()
                .map(companyMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public CompanyDTO updateCompany(Long id, CompanyDTO companyDTO) {
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + id));
        
        // Check if name already exists for another company
        if (!existingCompany.getName().equals(companyDTO.getName()) && 
                companyRepository.existsByName(companyDTO.getName())) {
            throw new CompanyAlreadyExistsException("Company already exists with name: " + companyDTO.getName());
        }
        
        // Update company properties
        existingCompany.setName(companyDTO.getName());
        existingCompany.setDescription(companyDTO.getDescription());
        existingCompany.setAddress(companyDTO.getAddress());
        existingCompany.setCity(companyDTO.getCity());
        existingCompany.setState(companyDTO.getState());
        existingCompany.setZipCode(companyDTO.getZipCode());
        existingCompany.setCountry(companyDTO.getCountry());
        existingCompany.setPhoneNumber(companyDTO.getPhoneNumber());
        existingCompany.setEmail(companyDTO.getEmail());
        existingCompany.setWebsite(companyDTO.getWebsite());
        existingCompany.setLogoUrl(companyDTO.getLogoUrl());
        existingCompany.setUpdatedAt(LocalDateTime.now());
        
        // Save updated company
        Company updatedCompany = companyRepository.save(existingCompany);
        
        return companyMapper.toDTO(updatedCompany);
    }
    
    @Override
    @Transactional
    public void deleteCompany(Long id) {
        // Check if company exists
        if (!companyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Company not found with id: " + id);
        }
        
        companyRepository.deleteById(id);
    }
}