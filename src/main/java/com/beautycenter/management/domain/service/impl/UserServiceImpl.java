package com.beautycenter.management.domain.service.impl;

import com.beautycenter.management.domain.event.DomainEventPublisher;
import com.beautycenter.management.domain.event.user.UserCreatedEvent;
import com.beautycenter.management.domain.event.user.UserUpdatedEvent;
import com.beautycenter.management.domain.model.User;
import com.beautycenter.management.domain.repository.UserRepository;
import com.beautycenter.management.domain.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation of the UserService interface.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DomainEventPublisher eventPublisher;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, DomainEventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.eventPublisher = eventPublisher;
    }

    @Override
    @Transactional
    public User createUser(String username, String password, String fullName, String email, String phoneNumber, List<String> roles) {
        logger.debug("Creating user with username: {}", username);

        // Validate username uniqueness
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists: " + username);
        }

        // Validate email uniqueness if provided
        if (email != null && !email.isBlank() && userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists: " + email);
        }

        // Encrypt password
        String encryptedPassword = passwordEncoder.encode(password);

        // Create user
        User user = new User(
                username,
                encryptedPassword,
                fullName,
                email,
                phoneNumber,
                roles != null ? new HashSet<>(roles) : new HashSet<>()
        );

        // Save user
        User savedUser = userRepository.save(user);

        // Publish event
        eventPublisher.publish(new UserCreatedEvent(savedUser));

        logger.info("User created with ID: {}", savedUser.getId());
        return savedUser;
    }

    @Override
    @Transactional
    public User updateUser(UUID id, String fullName, String email, String phoneNumber) {
        logger.debug("Updating user with ID: {}", id);

        // Find user
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));

        // Validate email uniqueness if changing
        if (email != null && !email.equals(user.getEmail()) && userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists: " + email);
        }

        // Update user
        user.updateContactInfo(fullName, email, phoneNumber);

        // Save user
        User savedUser = userRepository.save(user);

        // Publish event
        eventPublisher.publish(new UserUpdatedEvent(savedUser));

        logger.info("User updated with ID: {}", savedUser.getId());
        return savedUser;
    }

    @Override
    @Transactional
    public User updatePassword(UUID id, String oldPassword, String newPassword) {
        logger.debug("Updating password for user with ID: {}", id);

        // Find user
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));

        // Verify old password
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("Incorrect old password");
        }

        // Encrypt new password
        String encryptedPassword = passwordEncoder.encode(newPassword);

        // Update password
        user.updatePassword(encryptedPassword);

        // Save user
        User savedUser = userRepository.save(user);

        logger.info("Password updated for user with ID: {}", savedUser.getId());
        return savedUser;
    }

    @Override
    @Transactional
    public User addRole(UUID id, String role) {
        logger.debug("Adding role {} to user with ID: {}", role, id);

        // Find user
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));

        // Add role
        boolean added = user.addRole(role);

        if (added) {
            // Save user
            User savedUser = userRepository.save(user);

            // Publish event
            eventPublisher.publish(new UserUpdatedEvent(savedUser));

            logger.info("Role {} added to user with ID: {}", role, savedUser.getId());
            return savedUser;
        } else {
            logger.info("Role {} already exists for user with ID: {}", role, id);
            return user;
        }
    }

    @Override
    @Transactional
    public User removeRole(UUID id, String role) {
        logger.debug("Removing role {} from user with ID: {}", role, id);

        // Find user
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));

        // Remove role
        boolean removed = user.removeRole(role);

        if (removed) {
            // Save user
            User savedUser = userRepository.save(user);

            // Publish event
            eventPublisher.publish(new UserUpdatedEvent(savedUser));

            logger.info("Role {} removed from user with ID: {}", role, savedUser.getId());
            return savedUser;
        } else {
            logger.info("Role {} does not exist for user with ID: {}", role, id);
            return user;
        }
    }

    @Override
    @Transactional
    public User deactivateUser(UUID id) {
        logger.debug("Deactivating user with ID: {}", id);

        // Find user
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));

        // Deactivate user
        user.deactivate();

        // Save user
        User savedUser = userRepository.save(user);

        // Publish event
        eventPublisher.publish(new UserUpdatedEvent(savedUser));

        logger.info("User deactivated with ID: {}", savedUser.getId());
        return savedUser;
    }

    @Override
    @Transactional
    public User activateUser(UUID id) {
        logger.debug("Activating user with ID: {}", id);

        // Find user
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));

        // Activate user
        user.activate();

        // Save user
        User savedUser = userRepository.save(user);

        // Publish event
        eventPublisher.publish(new UserUpdatedEvent(savedUser));

        logger.info("User activated with ID: {}", savedUser.getId());
        return savedUser;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(UUID id) {
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
    public List<User> findByRole(String role) {
        logger.debug("Finding users with role: {}", role);
        return userRepository.findByRole(role);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        logger.debug("Deleting user with ID: {}", id);

        // Validate user exists
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }

        // Delete user
        userRepository.deleteById(id);

        logger.info("User deleted with ID: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> authenticate(String username, String password) {
        logger.debug("Authenticating user with username: {}", username);

        // Find user
        Optional<User> optionalUser = userRepository.findByUsername(username);

        // Verify password if user exists
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            
            if (passwordEncoder.matches(password, user.getPassword()) && user.isActive()) {
                logger.info("User authenticated successfully: {}", username);
                return optionalUser;
            }
        }

        logger.info("Authentication failed for username: {}", username);
        return Optional.empty();
    }
}