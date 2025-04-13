package com.beautycenter.management.domain.service.exception;

/**
 * Exception thrown when attempting to create a company that already exists.
 */
public class CompanyAlreadyExistsException extends RuntimeException {
    
    public CompanyAlreadyExistsException(String message) {
        super(message);
    }
    
    public CompanyAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}