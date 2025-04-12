package com.beautycenter.management.infrastructure.persistence.adapter;

import com.beautycenter.management.domain.model.Company;
import com.beautycenter.management.domain.repository.CompanyRepository;
import com.beautycenter.management.infrastructure.persistence.entity.CompanyEntity;
import com.beautycenter.management.infrastructure.persistence.mapper.CompanyMapper;
import com.beautycenter.management.infrastructure.persistence.repository.CompanyJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Adapter implementation of CompanyRepository.
 * Bridges the domain with the JPA infrastructure.
 */
@Repository
@RequiredArgsConstructor
public class CompanyRepositoryAdapter implements CompanyRepository {
    
    private final CompanyJpaRepository jpaRepository;
    private final CompanyMapper mapper;
    
    @Override
    public Company save(Company company) {
        CompanyEntity entity = mapper.toEntity(company);
        CompanyEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<Company> findById(UUID id) {
        // Since the JPA repository uses Long IDs and domain uses UUIDs,
        // we'll need to map between them.
        // For a real application, you would store UUIDs in the database.
        // This is a simplified approach for demo purposes.
        String idStr = id.toString();
        // Find by UUID string representation as a unique field or convert to numeric ID
        return jpaRepository.findByUuid(idStr)
                .map(mapper::toDomain);
    }
    
    @Override
    public List<Company> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<Company> findByName(String name) {
        return jpaRepository.findByName(name)
                .map(mapper::toDomain);
    }
    
    @Override
    public void deleteById(UUID id) {
        String idStr = id.toString();
        jpaRepository.findByUuid(idStr).ifPresent(entity -> 
            jpaRepository.deleteById(entity.getId())
        );
    }
    
    @Override
    public boolean existsById(UUID id) {
        String idStr = id.toString();
        return jpaRepository.existsByUuid(idStr);
    }
    
    @Override
    public boolean existsByName(String name) {
        return jpaRepository.existsByName(name);
    }
    
    @Override
    public List<Company> findByActiveTrue() {
        return jpaRepository.findByActiveTrue().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Company> findByNameContaining(String name) {
        return jpaRepository.findByNameContaining(name).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}