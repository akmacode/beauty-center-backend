package com.beautycenter.management.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Abstract base class for domain events.
 * Provides common functionality for all domain events.
 */
public abstract class AbstractDomainEvent implements DomainEvent {
    
    private final UUID eventId;
    private final LocalDateTime timestamp;
    private final String eventType;
    
    protected AbstractDomainEvent(String eventType) {
        this.eventId = UUID.randomUUID();
        this.timestamp = LocalDateTime.now();
        this.eventType = eventType;
    }
    
    @Override
    public UUID getEventId() {
        return eventId;
    }
    
    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    @Override
    public String getEventType() {
        return eventType;
    }
}