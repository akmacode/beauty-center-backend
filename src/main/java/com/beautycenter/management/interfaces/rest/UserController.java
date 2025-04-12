package com.beautycenter.management.interfaces.rest;

import com.beautycenter.management.application.dto.UserDTO;
import com.beautycenter.management.application.service.UserApplicationService;
import com.beautycenter.management.domain.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * REST controller for User operations.
 * This controller uses the application service instead of the domain service directly.
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserApplicationService userApplicationService;
    
    /**
     * Create a new user.
     *
     * @param userDTO the user data
     * @return the created user
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userApplicationService.createUser(userDTO), HttpStatus.CREATED);
    }
    
    /**
     * Get user by ID.
     *
     * @param id the user ID
     * @return the user
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE', 'RECEPTIONIST')")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userApplicationService.getUserById(id));
    }
    
    /**
     * Get all users.
     *
     * @return list of all users
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE', 'RECEPTIONIST')")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userApplicationService.getAllUsers());
    }
    
    /**
     * Get users by company ID.
     *
     * @param companyId the company ID
     * @return list of users for the company
     */
    @GetMapping("/company/{companyId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE', 'RECEPTIONIST')")
    public ResponseEntity<List<UserDTO>> getUsersByCompanyId(@PathVariable Long companyId) {
        return ResponseEntity.ok(userApplicationService.getUsersByCompanyId(companyId));
    }
    
    /**
     * Get users by role.
     *
     * @param role the role
     * @return list of users with the specified role
     */
    @GetMapping("/role/{role}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<List<UserDTO>> getUsersByRole(@PathVariable Role role) {
        return ResponseEntity.ok(userApplicationService.getUsersByRole(role));
    }
    
    /**
     * Update user.
     *
     * @param id the user ID
     * @param userDTO the updated user data
     * @return the updated user
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userApplicationService.updateUser(id, userDTO));
    }
    
    /**
     * Change user active status.
     *
     * @param id the user ID
     * @param active the active status
     * @return the updated user
     */
    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> changeUserStatus(@PathVariable Long id, @RequestParam boolean active) {
        return ResponseEntity.ok(userApplicationService.changeUserStatus(id, active));
    }
    
    /**
     * Delete user.
     *
     * @param id the user ID
     * @return empty response
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userApplicationService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * Change user password.
     *
     * @param id the user ID
     * @param currentPassword the current password
     * @param newPassword the new password
     * @return the updated user
     */
    @PatchMapping("/{id}/password")
    @PreAuthorize("hasAnyRole('ADMIN') or authentication.principal.id == #id")
    public ResponseEntity<UserDTO> changePassword(
            @PathVariable Long id,
            @RequestParam String currentPassword,
            @RequestParam String newPassword) {
        return ResponseEntity.ok(userApplicationService.changePassword(id, currentPassword, newPassword));
    }
}