package com.beautycenter.management.domain.service;

import com.beautycenter.management.domain.event.DomainEvent;

/**
 * Service interface for audit logging.
 * This interface defines methods for logging audit events.
 */
public interface AuditService {
    
    /**
     * Log an audit event.
     *
     * @param event the domain event to log
     * @param userId the ID of the user who performed the action (if available)
     * @param description a description of the audit event
     */
    void logEvent(DomainEvent event, Long userId, String description);
    
    /**
     * Log an audit event.
     *
     * @param event the domain event to log
     * @param description a description of the audit event
     */
    void logEvent(DomainEvent event, String description);
}