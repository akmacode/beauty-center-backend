package com.beautycenter.management.infrastructure.persistence.repository;

import com.beautycenter.management.infrastructure.persistence.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for CompanyEntity.
 */
@Repository
public interface CompanyJpaRepository extends JpaRepository<CompanyEntity, Long> {
    
    /**
     * Find a company by name.
     *
     * @param name the company name
     * @return an Optional containing the company if found, empty otherwise
     */
    Optional<CompanyEntity> findByName(String name);
    
    /**
     * Check if a company exists by name.
     *
     * @param name the company name
     * @return true if exists, false otherwise
     */
    boolean existsByName(String name);
}