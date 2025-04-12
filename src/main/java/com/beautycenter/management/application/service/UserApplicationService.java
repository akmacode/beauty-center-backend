package com.beautycenter.management.application.service;

import com.beautycenter.management.application.dto.UserDTO;
import com.beautycenter.management.application.mapper.UserMapper;
import com.beautycenter.management.domain.model.Role;
import com.beautycenter.management.domain.model.User;
import com.beautycenter.management.domain.service.UserService;
import com.beautycenter.management.domain.service.exception.ResourceNotFoundException;
import com.beautycenter.management.domain.service.exception.UserAlreadyExistsException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Application service for User operations.
 * This service maps between DTOs and domain models, and delegates domain operations
 * to the domain service.
 */
@Service
public class UserApplicationService {
    
    private static final Logger logger = LoggerFactory.getLogger(UserApplicationService.class);
    
    private final UserService userService;
    private final UserMapper userMapper;
    
    public UserApplicationService(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }
    
    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        logger.debug("Creating user with username: {}", userDTO.getUsername());
        
        // Check if username or email already exists
        if (userService.existsByUsername(userDTO.getUsername())) {
            throw new UserAlreadyExistsException("Username already exists: " + userDTO.getUsername());
        }
        
        if (userService.existsByEmail(userDTO.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists: " + userDTO.getEmail());
        }
        
        // Map DTO to domain model
        User user = userMapper.toDomain(userDTO);
        
        // Create user
        User createdUser = userService.createUser(user);
        
        // Map result back to DTO
        return userMapper.toDTO(createdUser);
    }
    
    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        logger.debug("Getting user by ID: {}", id);
        
        User user = userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        
        return userMapper.toDTO(user);
    }
    
    @Transactional(readOnly = true)
    public UserDTO getUserByUsername(String username) {
        logger.debug("Getting user by username: {}", username);
        
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
        
        return userMapper.toDTO(user);
    }
    
    @Transactional(readOnly = true)
    public UserDTO getUserByEmail(String email) {
        logger.debug("Getting user by email: {}", email);
        
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        
        return userMapper.toDTO(user);
    }
    
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        logger.debug("Getting all users");
        
        return userService.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<UserDTO> getUsersByCompanyId(Long companyId) {
        logger.debug("Getting users by company ID: {}", companyId);
        
        return userService.findByCompanyId(companyId).stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<UserDTO> getUsersByRole(Role role) {
        logger.debug("Getting users by role: {}", role);
        
        return userService.findByRole(role).stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<UserDTO> getUsersByRoles(Set<Role> roles) {
        logger.debug("Getting users by roles: {}", roles);
        
        return userService.findByRolesIn(roles).stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        logger.debug("Updating user with ID: {}", id);
        
        // Check if user exists
        if (!userService.existsById(id)) {
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }
        
        // Map DTO to domain model
        User userDetails = userMapper.toDomain(userDTO);
        
        // Update user
        User updatedUser = userService.updateUser(id, userDetails);
        
        // Map result back to DTO
        return userMapper.toDTO(updatedUser);
    }
    
    @Transactional
    public void deleteUser(Long id) {
        logger.debug("Deleting user with ID: {}", id);
        
        // Delete user
        userService.deleteById(id);
    }
    
    @Transactional
    public UserDTO changeUserStatus(Long id, boolean active) {
        logger.debug("{} user with ID: {}", active ? "Activating" : "Deactivating", id);
        
        // Change status
        User updatedUser = userService.changeStatus(id, active);
        
        // Map result back to DTO
        return userMapper.toDTO(updatedUser);
    }
    
    @Transactional
    public UserDTO changePassword(Long id, String currentPassword, String newPassword) {
        logger.debug("Changing password for user with ID: {}", id);
        
        // Change password
        User updatedUser = userService.changePassword(id, currentPassword, newPassword);
        
        // Map result back to DTO
        return userMapper.toDTO(updatedUser);
    }
}