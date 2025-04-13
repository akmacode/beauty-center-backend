package com.beautycenter.management.infrastructure.persistence.adapter;

import com.beautycenter.management.domain.model.Service;
import com.beautycenter.management.domain.repository.ServiceRepository;
import com.beautycenter.management.infrastructure.persistence.entity.ServiceEntity;
import com.beautycenter.management.infrastructure.persistence.mapper.ServiceMapper;
import com.beautycenter.management.infrastructure.persistence.repository.ServiceJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Adapter implementation of ServiceRepository.
 * Bridges the domain with the JPA infrastructure.
 */
@Repository
@RequiredArgsConstructor
public class ServiceRepositoryAdapter implements ServiceRepository {
    
    private final ServiceJpaRepository jpaRepository;
    private final ServiceMapper mapper;
    
    @Override
    public Service save(Service service) {
        ServiceEntity entity = mapper.toEntity(service);
        ServiceEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<Service> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }
    
    @Override
    public List<Service> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Service> findByCompanyId(Long companyId) {
        return jpaRepository.findByCompanyId(companyId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Service> findByIdIn(Set<Long> ids) {
        return jpaRepository.findByIdIn(ids).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Service> findByCompanyIdAndActiveTrue(Long companyId) {
        return jpaRepository.findByCompanyIdAndActiveTrue(companyId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Service> findByCategory(String category) {
        return jpaRepository.findByCategory(category).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Service> findByCompanyIdAndCategory(Long companyId, String category) {
        return jpaRepository.findByCompanyIdAndCategory(companyId, category).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }
}