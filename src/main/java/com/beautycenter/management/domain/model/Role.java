package com.beautycenter.management.domain.model;

/**
 * User roles in the Beauty Center Management system.
 * These roles define the permissions and access levels in the application.
 */
public enum Role {
    /**
     * Basic user role with limited access.
     */
    USER,
    
    /**
     * Employee role for regular staff members.
     * Manages appointments and customer information.
     */
    EMPLOYEE,
    
    /**
     * Receptionist role for front desk operations.
     * Manages bookings, check-ins, and customer inquiries.
     */
    RECEPTIONIST,
    
    /**
     * Standardist role for specialists managing inventory and treatments.
     * Handles inventory, product standards, and treatment protocols.
     */
    STANDARDIST,
    
    /**
     * Admin role with full system access.
     * Manages users, system settings, and has unrestricted access.
     */
    ADMIN
}