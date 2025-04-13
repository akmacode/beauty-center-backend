package com.beautycenter.management.auth.application.service;

import com.beautycenter.management.auth.domain.model.RegistrationRequest;
import com.beautycenter.management.auth.domain.service.RegistrationService;
import com.beautycenter.management.domain.model.Role;
import com.beautycenter.management.domain.model.User;
import com.beautycenter.management.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of the RegistrationService interface.
 * This service handles user registration operations in the application.
 */
@Service
@AllArgsConstructor
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public User register(RegistrationRequest request) throws IllegalArgumentException {
        log.info("Processing registration request for username: {}", request.getUsername());

        // Validate request
        if (!request.isValid()) {
            log.warn("Invalid registration request");
            throw new IllegalArgumentException("Invalid registration request. Please provide all required fields.");
        }

        // Check if username exists
        if (usernameExists(request.getUsername())) {
            log.warn("Username already exists: {}", request.getUsername());
            throw new IllegalArgumentException("Username already exists");
        }

        // Check if email exists
        if (emailExists(request.getEmail())) {
            log.warn("Email already exists: {}", request.getEmail());
            throw new IllegalArgumentException("Email already exists");
        }

        // Default to USER role if no roles specified
        Set<Role> roles = request.getRoles();
        if (roles == null || roles.isEmpty()) {
            roles = new HashSet<>();
            roles.add(Role.USER);
        }

        // Create user entity
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword())) // Encrypt password
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .roles(roles)
                .active(true)
                .build();

        // Save user
        User savedUser = userRepository.save(user);
        log.info("Successfully registered user with ID: {}", savedUser.getId());
        
        return savedUser;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}