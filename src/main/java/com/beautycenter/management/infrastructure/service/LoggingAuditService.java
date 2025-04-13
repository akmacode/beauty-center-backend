package com.beautycenter.management.infrastructure.service;

import com.beautycenter.management.domain.event.DomainEvent;
import com.beautycenter.management.domain.service.AuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Implementation of the AuditService that logs audit events to the application log.
 * This is a simplified implementation that could be extended to store audit logs
 * in a database or external system.
 */
@Service
public class LoggingAuditService implements AuditService {
    
    private static final Logger logger = LoggerFactory.getLogger("AUDIT");
    
    @Override
    public void logEvent(DomainEvent event, Long userId, String description) {
        logger.info("AUDIT: [{}] (Event ID: {}, User ID: {}) - {}",
                event.getEventType(),
                event.getEventId(),
                userId,
                description);
    }
    
    @Override
    public void logEvent(DomainEvent event, String description) {
        logger.info("AUDIT: [{}] (Event ID: {}) - {}",
                event.getEventType(),
                event.getEventId(),
                description);
    }
}