package com.beautycenter.management.interfaces.rest;

import com.beautycenter.management.application.service.CompanyApplicationService;
import com.beautycenter.management.domain.model.Company;
import com.beautycenter.management.interfaces.rest.dto.CompanyDto;
import com.beautycenter.management.interfaces.rest.dto.LocationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * REST controller for Company-related operations.
 */
@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {
    
    private final CompanyApplicationService companyService;
    
    /**
     * Create a new company.
     *
     * @param companyDto the company data
     * @return the created company
     */
    @PostMapping
    public ResponseEntity<CompanyDto> createCompany(@Valid @RequestBody CompanyDto companyDto) {
        Company company = mapToEntity(companyDto);
        Company createdCompany = companyService.createCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToDto(createdCompany));
    }
    
    /**
     * Get all companies.
     *
     * @return list of all companies
     */
    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        List<Company> companies = companyService.findAll();
        List<CompanyDto> companyDtos = companies.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(companyDtos);
    }
    
    /**
     * Get active companies.
     *
     * @return list of active companies
     */
    @GetMapping("/active")
    public ResponseEntity<List<CompanyDto>> getActiveCompanies() {
        List<Company> companies = companyService.findActiveCompanies();
        List<CompanyDto> companyDtos = companies.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(companyDtos);
    }
    
    /**
     * Search companies by name.
     *
     * @param name the name to search for
     * @return list of matching companies
     */
    @GetMapping("/search")
    public ResponseEntity<List<CompanyDto>> searchCompaniesByName(@RequestParam String name) {
        List<Company> companies = companyService.findByNameContaining(name);
        List<CompanyDto> companyDtos = companies.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(companyDtos);
    }
    
    /**
     * Get a company by ID.
     *
     * @param id the company ID
     * @return the company
     */
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable UUID id) {
        Company company = companyService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found with ID: " + id));
        return ResponseEntity.ok(mapToDto(company));
    }
    
    /**
     * Update a company.
     *
     * @param id the company ID
     * @param companyDto the updated company data
     * @return the updated company
     */
    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> updateCompany(@PathVariable UUID id, @Valid @RequestBody CompanyDto companyDto) {
        companyDto.setId(id); // Ensure the ID is set correctly
        
        try {
            Company company = mapToEntity(companyDto);
            Company updatedCompany = companyService.updateCompany(id, company);
            return ResponseEntity.ok(mapToDto(updatedCompany));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    
    /**
     * Delete a company.
     *
     * @param id the company ID
     * @return no content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable UUID id) {
        try {
            companyService.deleteCompany(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    
    /**
     * Deactivate a company.
     *
     * @param id the company ID
     * @return the deactivated company
     */
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<CompanyDto> deactivateCompany(@PathVariable UUID id) {
        try {
            Company deactivatedCompany = companyService.deactivateCompany(id);
            return ResponseEntity.ok(mapToDto(deactivatedCompany));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    
    /**
     * Activate a company.
     *
     * @param id the company ID
     * @return the activated company
     */
    @PatchMapping("/{id}/activate")
    public ResponseEntity<CompanyDto> activateCompany(@PathVariable UUID id) {
        try {
            Company activatedCompany = companyService.activateCompany(id);
            return ResponseEntity.ok(mapToDto(activatedCompany));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    
    /**
     * Maps a Company domain model to a CompanyDto.
     *
     * @param company the domain model to map
     * @return the DTO
     */
    private CompanyDto mapToDto(Company company) {
        CompanyDto dto = CompanyDto.builder()
                .id(company.getId())
                .name(company.getName())
                .description(company.getDescription())
                .logoUrl(company.getLogoUrl())
                .address(company.getAddress())
                .city(company.getCity())
                .state(company.getState())
                .country(company.getCountry())
                .zipCode(company.getZipCode())
                .phoneNumber(company.getPhoneNumber())
                .email(company.getEmail())
                .website(company.getWebsite())
                .active(company.isActive())
                .build();
        
        // Map locations if present
        if (company.getLocations() != null && !company.getLocations().isEmpty()) {
            dto.setLocations(company.getLocations().stream()
                    .map(this::mapLocationToDto)
                    .collect(Collectors.toList()));
        }
        
        return dto;
    }
    
    /**
     * Maps a CompanyDto to a Company domain model.
     *
     * @param companyDto the DTO to map
     * @return the domain model
     */
    private Company mapToEntity(CompanyDto companyDto) {
        return Company.builder()
                .id(companyDto.getId())
                .name(companyDto.getName())
                .description(companyDto.getDescription())
                .logoUrl(companyDto.getLogoUrl())
                .address(companyDto.getAddress())
                .city(companyDto.getCity())
                .state(companyDto.getState())
                .country(companyDto.getCountry())
                .zipCode(companyDto.getZipCode())
                .phoneNumber(companyDto.getPhoneNumber())
                .email(companyDto.getEmail())
                .website(companyDto.getWebsite())
                .active(companyDto.isActive())
                .build();
    }
    
    /**
     * Maps a Location domain model to a LocationDto.
     *
     * @param location the domain model to map
     * @return the DTO
     */
    private LocationDto mapLocationToDto(com.beautycenter.management.domain.model.Location location) {
        return LocationDto.builder()
                .id(location.getId())
                .name(location.getName())
                .address(location.getAddress())
                .city(location.getCity())
                .state(location.getState())
                .country(location.getCountry())
                .zipCode(location.getZipCode())
                .phoneNumber(location.getPhoneNumber())
                .email(location.getEmail())
                .active(location.isActive())
                .companyId(location.getCompanyId())
                .build();
    }
}