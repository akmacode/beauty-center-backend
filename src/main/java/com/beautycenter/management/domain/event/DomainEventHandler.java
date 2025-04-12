package com.beautycenter.management.domain.event;

/**
 * Interface for domain event handlers.
 * Handlers are responsible for reacting to domain events.
 *
 * @param <T> the type of domain event this handler can process
 */
public interface DomainEventHandler<T extends DomainEvent> {
    
    /**
     * Handle the given domain event.
     *
     * @param event the event to handle
     */
    void handle(T event);
    
    /**
     * Get the class of domain event this handler can process.
     *
     * @return the event class
     */
    Class<T> getEventType();
}