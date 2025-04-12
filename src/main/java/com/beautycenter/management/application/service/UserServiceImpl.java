package com.beautycenter.management.application.service;

import com.beautycenter.management.application.dto.UserDTO;
import com.beautycenter.management.application.mapper.UserMapper;
import com.beautycenter.management.domain.model.Role;
import com.beautycenter.management.domain.model.User;
import com.beautycenter.management.domain.service.UserService;
import com.beautycenter.management.domain.service.exception.ResourceNotFoundException;
import com.beautycenter.management.domain.service.exception.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Legacy implementation of the UserApplicationService.
 * This is kept for backward compatibility but will be replaced by UserApplicationService.
 * @deprecated Use {@link UserApplicationService} instead
 */
@Service
@RequiredArgsConstructor
@Deprecated
public class UserServiceImpl {
    
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    
    private final UserApplicationService userApplicationService;
    
    /**
     * Create a new user.
     *
     * @param userDTO the user data
     * @return the created user
     */
    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        logger.warn("Deprecated method createUser called. Use UserApplicationService instead.");
        return userApplicationService.createUser(userDTO);
    }
    
    /**
     * Get user by ID.
     *
     * @param id the user ID
     * @return the user
     */
    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        logger.warn("Deprecated method getUserById called. Use UserApplicationService instead.");
        return userApplicationService.getUserById(id);
    }
    
    /**
     * Get user by username.
     *
     * @param username the username
     * @return the user
     */
    @Transactional(readOnly = true)
    public UserDTO getUserByUsername(String username) {
        logger.warn("Deprecated method getUserByUsername called. Use UserApplicationService instead.");
        return userApplicationService.getUserByUsername(username);
    }
    
    /**
     * Get user by email.
     *
     * @param email the email
     * @return the user
     */
    @Transactional(readOnly = true)
    public UserDTO getUserByEmail(String email) {
        logger.warn("Deprecated method getUserByEmail called. Use UserApplicationService instead.");
        return userApplicationService.getUserByEmail(email);
    }
    
    /**
     * Get all users.
     *
     * @return list of all users
     */
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        logger.warn("Deprecated method getAllUsers called. Use UserApplicationService instead.");
        return userApplicationService.getAllUsers();
    }
    
    /**
     * Get users by company ID.
     *
     * @param companyId the company ID
     * @return list of users for the company
     */
    @Transactional(readOnly = true)
    public List<UserDTO> getUsersByCompanyId(Long companyId) {
        logger.warn("Deprecated method getUsersByCompanyId called. Use UserApplicationService instead.");
        return userApplicationService.getUsersByCompanyId(companyId);
    }
    
    /**
     * Get users by role.
     *
     * @param role the role
     * @return list of users with the specified role
     */
    @Transactional(readOnly = true)
    public List<UserDTO> getUsersByRole(Role role) {
        logger.warn("Deprecated method getUsersByRole called. Use UserApplicationService instead.");
        return userApplicationService.getUsersByRole(role);
    }
    
    /**
     * Get users by roles.
     *
     * @param roles the roles
     * @return list of users with any of the specified roles
     */
    @Transactional(readOnly = true)
    public List<UserDTO> getUsersByRoles(Set<Role> roles) {
        logger.warn("Deprecated method getUsersByRoles called. Use UserApplicationService instead.");
        return userApplicationService.getUsersByRoles(roles);
    }
    
    /**
     * Update user.
     *
     * @param id the user ID
     * @param userDTO the updated user data
     * @return the updated user
     */
    @Transactional
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        logger.warn("Deprecated method updateUser called. Use UserApplicationService instead.");
        return userApplicationService.updateUser(id, userDTO);
    }
    
    /**
     * Delete user.
     *
     * @param id the user ID
     */
    @Transactional
    public void deleteUser(Long id) {
        logger.warn("Deprecated method deleteUser called. Use UserApplicationService instead.");
        userApplicationService.deleteUser(id);
    }
    
    /**
     * Change user active status.
     *
     * @param id the user ID
     * @param active the active status
     * @return the updated user
     */
    @Transactional
    public UserDTO changeUserStatus(Long id, boolean active) {
        logger.warn("Deprecated method changeUserStatus called. Use UserApplicationService instead.");
        return userApplicationService.changeUserStatus(id, active);
    }
}