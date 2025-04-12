package com.beautycenter.management.domain.event;

/**
 * Interface for publishing domain events.
 * This is part of the event-driven architecture pattern.
 */
public interface DomainEventPublisher {
    
    /**
     * Publish a domain event.
     *
     * @param event the event to publish
     */
    void publish(DomainEvent event);
}