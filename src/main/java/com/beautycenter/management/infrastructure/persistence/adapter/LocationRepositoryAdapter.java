package com.beautycenter.management.infrastructure.persistence.adapter;

import com.beautycenter.management.domain.model.Location;
import com.beautycenter.management.domain.repository.LocationRepository;
import com.beautycenter.management.infrastructure.persistence.entity.LocationEntity;
import com.beautycenter.management.infrastructure.persistence.mapper.LocationMapper;
import com.beautycenter.management.infrastructure.persistence.repository.LocationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adapter implementation of LocationRepository.
 * Bridges the domain with the JPA infrastructure.
 */
@Repository
@RequiredArgsConstructor
public class LocationRepositoryAdapter implements LocationRepository {

    private final LocationJpaRepository jpaRepository;
    private final LocationMapper mapper;
    
    @Override
    public Location save(Location location) {
        LocationEntity entity = mapper.toEntity(location);
        LocationEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<Location> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }
    
    @Override
    public List<Location> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Location> findByCompanyId(Long companyId) {
        return jpaRepository.findByCompanyId(companyId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Location> findByCompanyIdAndActiveTrue(Long companyId) {
        return jpaRepository.findByCompanyIdAndActiveTrue(companyId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<Location> findByCompanyIdAndName(Long companyId, String name) {
        return jpaRepository.findByCompanyIdAndName(companyId, name)
                .map(mapper::toDomain);
    }
    
    @Override
    public List<Location> findByCity(String city) {
        return jpaRepository.findByCity(city).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Location> findByState(String state) {
        return jpaRepository.findByState(state).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Location> findByCityAndState(String city, String state) {
        return jpaRepository.findByCityAndState(city, state).stream()
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
    
    @Override
    public boolean existsByCompanyIdAndName(Long companyId, String name) {
        return jpaRepository.existsByCompanyIdAndName(companyId, name);
    }
}