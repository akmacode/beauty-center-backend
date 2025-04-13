package com.beautycenter.management.infrastructure.event;

import com.beautycenter.management.domain.event.user.*;
import com.beautycenter.management.domain.service.AuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Listener for user-related domain events.
 * This class handles all user events and performs necessary actions.
 */
@Component
public class UserEventListener {
    
    private static final Logger logger = LoggerFactory.getLogger(UserEventListener.class);
    
    private final AuditService auditService;
    
    public UserEventListener(AuditService auditService) {
        this.auditService = auditService;
    }
    
    @Async
    @EventListener
    public void handleUserCreatedEvent(UserCreatedEvent event) {
        logger.info("User created event received: User ID={}, Username={}", 
                event.getUser().getId(), event.getUser().getUsername());
        
        // Audit logging
        auditService.logEvent(event, 
                event.getUser().getId(), 
                String.format("User created: %s (%s)", 
                        event.getUser().getUsername(), 
                        event.getUser().getEmail()));
        
        // Here you can add additional logic like:
        // 1. Sending welcome emails
        // 2. Creating default resources for the user
        // 3. Notifying other systems
    }
    
    @Async
    @EventListener
    public void handleUserUpdatedEvent(UserUpdatedEvent event) {
        logger.info("User updated event received: User ID={}, Username={}", 
                event.getUser().getId(), event.getUser().getUsername());
        
        // Audit logging
        auditService.logEvent(event, 
                event.getUser().getId(), 
                String.format("User updated: %s (%s)", 
                        event.getUser().getUsername(), 
                        event.getUser().getEmail()));
        
        // Here you can add additional logic like:
        // 1. Updating related resources
        // 2. Notifying other systems
    }
    
    @Async
    @EventListener
    public void handleUserDeletedEvent(UserDeletedEvent event) {
        logger.info("User deleted event received: User ID={}, Username={}", 
                event.getUser().getId(), event.getUser().getUsername());
        
        // Audit logging
        auditService.logEvent(event, 
                String.format("User deleted: %s (ID: %d)", 
                        event.getUser().getUsername(), 
                        event.getUser().getId()));
        
        // Here you can add additional logic like:
        // 1. Cleaning up related resources
        // 2. Notifying other systems
    }
    
    @Async
    @EventListener
    public void handleUserActivatedEvent(UserActivatedEvent event) {
        logger.info("User activated event received: User ID={}, Username={}", 
                event.getUser().getId(), event.getUser().getUsername());
        
        // Audit logging
        auditService.logEvent(event, 
                event.getUser().getId(), 
                String.format("User activated: %s", 
                        event.getUser().getUsername()));
        
        // Here you can add additional logic like:
        // 1. Sending activation confirmation emails
        // 2. Enabling related resources
        // 3. Notifying other systems
    }
    
    @Async
    @EventListener
    public void handleUserDeactivatedEvent(UserDeactivatedEvent event) {
        logger.info("User deactivated event received: User ID={}, Username={}", 
                event.getUser().getId(), event.getUser().getUsername());
        
        // Audit logging
        auditService.logEvent(event, 
                event.getUser().getId(), 
                String.format("User deactivated: %s", 
                        event.getUser().getUsername()));
        
        // Here you can add additional logic like:
        // 1. Sending deactivation notifications
        // 2. Disabling related resources
        // 3. Notifying other systems
    }
}