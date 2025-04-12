package com.beautycenter.management.interfaces.rest;

import com.beautycenter.management.application.dto.CompanyDTO;
import com.beautycenter.management.application.service.CompanyApplicationService;
import com.beautycenter.management.application.mapper.CompanyMapper;
import com.beautycenter.management.domain.service.exception.ResourceNotFoundException;
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
 * This controller uses CompanyApplicationService for all operations and works with DTOs.
 */
@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {
    
    private final CompanyApplicationService companyApplicationService;
    private final CompanyMapper companyMapper;
    
    /**
     * Create a new company.
     *
     * @param companyDto the company data
     * @return the created company
     */
    @PostMapping
    public ResponseEntity<CompanyDto> createCompany(@Valid @RequestBody CompanyDto companyDto) {
        try {
            // First convert our controller DTO to application DTO
            CompanyDTO applicationDto = convertToApplicationDTO(companyDto);
            
            // Create the company using the application service
            CompanyDTO createdCompany = companyApplicationService.createCompany(applicationDto);
            
            // Convert back to controller DTO for response
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToControllerDTO(createdCompany));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    
    /**
     * Get all companies.
     *
     * @return list of all companies
     */
    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        List<CompanyDTO> companies = companyApplicationService.getAllCompanies();
        List<CompanyDto> companyDtos = companies.stream()
                .map(this::convertToControllerDTO)
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
        List<CompanyDTO> companies = companyApplicationService.getActiveCompanies();
        List<CompanyDto> companyDtos = companies.stream()
                .map(this::convertToControllerDTO)
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
        List<CompanyDTO> companies = companyApplicationService.searchCompaniesByName(name);
        List<CompanyDto> companyDtos = companies.stream()
                .map(this::convertToControllerDTO)
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
        try {
            // Convert UUID to Long since our application layer uses Long IDs
            Long longId = id.getMostSignificantBits();
            CompanyDTO company = companyApplicationService.getCompanyById(longId);
            return ResponseEntity.ok(convertToControllerDTO(company));
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    
    /**
     * Get a company by ID (using Long ID).
     *
     * @param id the company ID as Long
     * @return the company
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<CompanyDto> getCompanyByLongId(@PathVariable Long id) {
        try {
            CompanyDTO company = companyApplicationService.getCompanyById(id);
            return ResponseEntity.ok(convertToControllerDTO(company));
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    
    /**
     * Update a company.
     *
     * @param id the company ID
     * @param companyDto the updated company data
     * @return the updated company
     */
    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> updateCompany(@PathVariable Long id, @Valid @RequestBody CompanyDto companyDto) {
        try {
            CompanyDTO applicationDto = convertToApplicationDTO(companyDto);
            CompanyDTO updatedCompany = companyApplicationService.updateCompany(id, applicationDto);
            return ResponseEntity.ok(convertToControllerDTO(updatedCompany));
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
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
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        try {
            companyApplicationService.deleteCompany(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    
    /**
     * Deactivate a company.
     *
     * @param id the company ID
     * @return the deactivated company
     */
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<CompanyDto> deactivateCompany(@PathVariable Long id) {
        try {
            CompanyDTO deactivatedCompany = companyApplicationService.deactivateCompany(id);
            return ResponseEntity.ok(convertToControllerDTO(deactivatedCompany));
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    
    /**
     * Activate a company.
     *
     * @param id the company ID
     * @return the activated company
     */
    @PatchMapping("/{id}/activate")
    public ResponseEntity<CompanyDto> activateCompany(@PathVariable Long id) {
        try {
            CompanyDTO activatedCompany = companyApplicationService.activateCompany(id);
            return ResponseEntity.ok(convertToControllerDTO(activatedCompany));
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    
    /**
     * Converts application DTO to controller DTO.
     *
     * @param applicationDto the application DTO
     * @return the controller DTO
     */
    private CompanyDto convertToControllerDTO(CompanyDTO applicationDto) {
        CompanyDto dto = CompanyDto.builder()
                .id(applicationDto.getId() != null ? UUID.nameUUIDFromBytes(applicationDto.getId().toString().getBytes()) : null)
                .name(applicationDto.getName())
                .description(applicationDto.getDescription())
                .logoUrl(applicationDto.getLogoUrl())
                .address(applicationDto.getAddress())
                .city(applicationDto.getCity())
                .state(applicationDto.getState())
                .country(applicationDto.getCountry())
                .zipCode(applicationDto.getZipCode())
                .phoneNumber(applicationDto.getPhoneNumber())
                .email(applicationDto.getEmail())
                .website(applicationDto.getWebsite())
                .active(applicationDto.isActive())
                .build();
        
        // Locations would be handled in a similar way if needed
        
        return dto;
    }
    
    /**
     * Converts controller DTO to application DTO.
     *
     * @param controllerDto the controller DTO
     * @return the application DTO
     */
    private CompanyDTO convertToApplicationDTO(CompanyDto controllerDto) {
        CompanyDTO dto = new CompanyDTO();
        
        // Convert UUID to Long if present
        if (controllerDto.getId() != null) {
            dto.setId(controllerDto.getId().getMostSignificantBits());
        }
        
        dto.setName(controllerDto.getName());
        dto.setDescription(controllerDto.getDescription());
        dto.setLogoUrl(controllerDto.getLogoUrl());
        dto.setAddress(controllerDto.getAddress());
        dto.setCity(controllerDto.getCity());
        dto.setState(controllerDto.getState());
        dto.setCountry(controllerDto.getCountry());
        dto.setZipCode(controllerDto.getZipCode());
        dto.setPhoneNumber(controllerDto.getPhoneNumber());
        dto.setEmail(controllerDto.getEmail());
        dto.setWebsite(controllerDto.getWebsite());
        dto.setActive(controllerDto.isActive());
        
        return dto;
    }
}