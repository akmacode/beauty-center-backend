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
    public Optional<Company> findById(Long id) {
        return jpaRepository.findById(id)
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
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }
    
    @Override
    public boolean existsByName(String name) {
        return jpaRepository.existsByName(name);
    }
}