package com.beautycenter.management.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Base interface for all domain events.
 * Domain events represent something that happened in the domain that domain experts care about.
 */
public interface DomainEvent {
    
    /**
     * Get the event ID.
     *
     * @return the event ID
     */
    UUID getEventId();
    
    /**
     * Get the event timestamp.
     *
     * @return the event timestamp
     */
    LocalDateTime getTimestamp();
    
    /**
     * Get the event type.
     *
     * @return the event type
     */
    String getEventType();
}