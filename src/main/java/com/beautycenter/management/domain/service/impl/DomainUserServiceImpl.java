package com.beautycenter.management.domain.service.impl;

import com.beautycenter.management.domain.event.DomainEventPublisher;
import com.beautycenter.management.domain.event.user.UserCreatedEvent;
import com.beautycenter.management.domain.event.user.UserDeactivatedEvent;
import com.beautycenter.management.domain.event.user.UserActivatedEvent;
import com.beautycenter.management.domain.event.user.UserUpdatedEvent;
import com.beautycenter.management.domain.model.Role;
import com.beautycenter.management.domain.model.User;
import com.beautycenter.management.domain.repository.UserRepository;
import com.beautycenter.management.domain.service.UserService;
import com.beautycenter.management.domain.service.exception.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Domain implementation of the UserService interface.
 * This implementation works with domain model objects only, not DTOs.
 */
@Service
@Primary
@Qualifier("domainUserService")
public class DomainUserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(DomainUserServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DomainEventPublisher eventPublisher;

    public DomainUserServiceImpl(UserRepository userRepository, 
                               PasswordEncoder passwordEncoder, 
                               DomainEventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.eventPublisher = eventPublisher;
    }

    @Override
    @Transactional
    public User createUser(User user) {
        logger.debug("Creating user with username: {}", user.getUsername());

        // Check if username or email already exists
        if (existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists: " + user.getUsername());
        }
        
        if (user.getEmail() != null && !user.getEmail().isEmpty() && existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + user.getEmail());
        }
        
        // Encrypt password if not already encrypted
        if (user.getPassword() != null && !user.getPassword().startsWith("$2a$")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        
        // Set additional properties if not set
        if (user.getCreatedAt() == null) {
            user.setCreatedAt(LocalDateTime.now());
        }
        
        if (user.getUpdatedAt() == null) {
            user.setUpdatedAt(LocalDateTime.now());
        }
        
        // Active flag is a primitive boolean so no null check needed
        // Just ensure it's set to true by default
        user.setActive(true);
        
        // Save user
        User savedUser = userRepository.save(user);
        
        // Publish event
        eventPublisher.publish(new UserCreatedEvent(savedUser));
        
        logger.info("User created with ID: {}", savedUser.getId());
        return savedUser;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        logger.debug("Finding user with ID: {}", id);
        return userRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        logger.debug("Finding user with username: {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        logger.debug("Finding user with email: {}", email);
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        logger.debug("Finding all users");
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findByCompanyId(Long companyId) {
        logger.debug("Finding users by company ID: {}", companyId);
        return userRepository.findByCompanyId(companyId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findByRole(Role role) {
        logger.debug("Finding users by role: {}", role);
        return userRepository.findByRole(role);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findByRolesIn(Set<Role> roles) {
        logger.debug("Finding users by roles: {}", roles);
        return userRepository.findByRolesIn(roles);
    }

    @Override
    @Transactional
    public User updateUser(Long id, User userDetails) {
        logger.debug("Updating user with ID: {}", id);

        // Find user
        User existingUser = findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        
        // Check if username already exists for another user
        if (!existingUser.getUsername().equals(userDetails.getUsername()) && 
                existsByUsername(userDetails.getUsername())) {
            throw new IllegalArgumentException("Username already exists: " + userDetails.getUsername());
        }
        
        // Check if email already exists for another user
        if (userDetails.getEmail() != null && !userDetails.getEmail().isEmpty() &&
                !existingUser.getEmail().equals(userDetails.getEmail()) && 
                existsByEmail(userDetails.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + userDetails.getEmail());
        }
        
        // Update user properties
        existingUser.setUsername(userDetails.getUsername());
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setFirstName(userDetails.getFirstName());
        existingUser.setLastName(userDetails.getLastName());
        existingUser.setPhoneNumber(userDetails.getPhoneNumber());
        existingUser.setRoles(userDetails.getRoles());
        existingUser.setCompanyId(userDetails.getCompanyId());
        existingUser.setUpdatedAt(LocalDateTime.now());
        
        // Update password if provided and different
        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty() &&
                !passwordEncoder.matches(userDetails.getPassword(), existingUser.getPassword())) {
            existingUser.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }
        
        // Save updated user
        User updatedUser = userRepository.save(existingUser);
        
        // Publish event
        eventPublisher.publish(new UserUpdatedEvent(updatedUser));
        
        logger.info("User updated with ID: {}", updatedUser.getId());
        return updatedUser;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        logger.debug("Deleting user with ID: {}", id);

        // Check if user exists
        if (!existsById(id)) {
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }
        
        userRepository.deleteById(id);
        logger.info("User deleted with ID: {}", id);
    }

    @Override
    @Transactional
    public User changeStatus(Long id, boolean active) {
        logger.debug("{} user with ID: {}", active ? "Activating" : "Deactivating", id);

        // Find user
        User user = findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        
        // Update status
        user.setActive(active);
        user.setUpdatedAt(LocalDateTime.now());
        
        // Save user
        User updatedUser = userRepository.save(user);
        
        // Publish event
        if (active) {
            eventPublisher.publish(new UserActivatedEvent(updatedUser));
        } else {
            eventPublisher.publish(new UserDeactivatedEvent(updatedUser));
        }
        
        logger.info("User {} with ID: {}", active ? "activated" : "deactivated", updatedUser.getId());
        return updatedUser;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public User changePassword(Long id, String currentPassword, String newPassword) {
        logger.debug("Changing password for user with ID: {}", id);

        // Find user
        User user = findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        
        // Verify current password
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect");
        }
        
        // Update password
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setUpdatedAt(LocalDateTime.now());
        
        // Save user
        User updatedUser = userRepository.save(user);
        
        // Publish event
        eventPublisher.publish(new UserUpdatedEvent(updatedUser));
        
        logger.info("Password changed for user with ID: {}", updatedUser.getId());
        return updatedUser;
    }

    @Override
    @Transactional
    public User activateUser(Long id) {
        return changeStatus(id, true);
    }

    @Override
    @Transactional
    public User deactivateUser(Long id) {
        return changeStatus(id, false);
    }
}