package com.beautycenter.management.infrastructure.persistence.repository;

import com.beautycenter.management.domain.model.Role;
import com.beautycenter.management.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Spring Data JPA repository for UserEntity.
 */
@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    
    /**
     * Find a user by username.
     *
     * @param username the username
     * @return an Optional containing the user if found, empty otherwise
     */
    Optional<UserEntity> findByUsername(String username);
    
    /**
     * Find a user by email.
     *
     * @param email the email
     * @return an Optional containing the user if found, empty otherwise
     */
    Optional<UserEntity> findByEmail(String email);
    
    /**
     * Find users by company ID.
     *
     * @param companyId the company ID
     * @return list of users belonging to the company
     */
    List<UserEntity> findByCompanyId(Long companyId);
    
    /**
     * Find users with a specific role.
     *
     * @param role the role
     * @return list of users with the specified role
     */
    @Query("SELECT u FROM UserEntity u JOIN u.roles r WHERE r = :role")
    List<UserEntity> findByRole(Role role);
    
    /**
     * Find users with any of the specified roles.
     *
     * @param roles the set of roles
     * @return list of users with any of the specified roles
     */
    @Query("SELECT DISTINCT u FROM UserEntity u JOIN u.roles r WHERE r IN :roles")
    List<UserEntity> findByRolesIn(Set<Role> roles);
    
    /**
     * Check if a username exists.
     *
     * @param username the username
     * @return true if exists, false otherwise
     */
    boolean existsByUsername(String username);
    
    /**
     * Check if an email exists.
     *
     * @param email the email
     * @return true if exists, false otherwise
     */
    boolean existsByEmail(String email);
}