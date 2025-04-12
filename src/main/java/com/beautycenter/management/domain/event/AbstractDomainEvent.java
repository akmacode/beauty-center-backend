package com.beautycenter.management.domain.event;

import java.time.Instant;
import java.util.UUID;

/**
 * Base implementation for domain events.
 * Provides common functionality for all domain events.
 */
public abstract class AbstractDomainEvent implements DomainEvent {
    
    private final UUID eventId;
    private final Instant occurredAt;
    
    /**
     * Create a new domain event with a generated ID and current timestamp.
     */
    protected AbstractDomainEvent() {
        this.eventId = UUID.randomUUID();
        this.occurredAt = Instant.now();
    }
    
    /**
     * Create a new domain event with the specified ID and timestamp.
     *
     * @param eventId the event ID
     * @param occurredAt the timestamp when the event occurred
     */
    protected AbstractDomainEvent(UUID eventId, Instant occurredAt) {
        this.eventId = eventId;
        this.occurredAt = occurredAt;
    }
    
    @Override
    public UUID getEventId() {
        return eventId;
    }
    
    @Override
    public Instant getOccurredAt() {
        return occurredAt;
    }
    
    @Override
    public String getEventType() {
        return this.getClass().getSimpleName();
    }
}