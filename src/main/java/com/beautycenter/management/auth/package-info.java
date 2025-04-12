/**
 * Authentication Module for Beauty Center Management Platform.
 * 
 * This module handles all authentication and authorization-related functionality:
 * - Login processing
 * - User registration
 * - JWT token generation and validation
 * - Security configurations
 * 
 * The module follows a clean layered architecture:
 * - controller: REST endpoints for authentication
 * - service: Business logic for authentication
 * - dto: Data Transfer Objects for request/response
 * - mapper: Conversion between domain entities and DTOs
 * - security: JWT token handling and security filters
 * - config: Security and authentication configurations
 */
package com.beautycenter.management.auth;