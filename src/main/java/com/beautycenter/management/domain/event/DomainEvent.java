package com.beautycenter.management.domain.event;

import java.time.Instant;
import java.util.UUID;

/**
 * Base interface for all domain events.
 * Domain events represent something that happened in the domain that domain experts care about.
 */
public interface DomainEvent {
    
    /**
     * Get the unique identifier of this event.
     *
     * @return the event ID
     */
    UUID getEventId();
    
    /**
     * Get the timestamp when this event occurred.
     *
     * @return the event timestamp
     */
    Instant getOccurredAt();
    
    /**
     * Get the type of this event.
     *
     * @return the event type
     */
    String getEventType();
}