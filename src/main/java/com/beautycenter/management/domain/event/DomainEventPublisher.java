package com.beautycenter.management.domain.event;

/**
 * Interface for domain event publishers.
 * Publishers are responsible for distributing domain events to interested handlers.
 */
public interface DomainEventPublisher {
    
    /**
     * Publish a domain event to all registered handlers.
     *
     * @param event the event to publish
     */
    void publish(DomainEvent event);
    
    /**
     * Register a domain event handler.
     *
     * @param handler the handler to register
     * @param <T> the type of domain event the handler can process
     */
    <T extends DomainEvent> void registerHandler(DomainEventHandler<T> handler);
    
    /**
     * Unregister a domain event handler.
     *
     * @param handler the handler to unregister
     * @param <T> the type of domain event the handler can process
     */
    <T extends DomainEvent> void unregisterHandler(DomainEventHandler<T> handler);
}