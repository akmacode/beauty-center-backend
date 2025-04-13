package com.beautycenter.management.infrastructure.event;

import com.beautycenter.management.domain.event.DomainEvent;
import com.beautycenter.management.domain.event.DomainEventHandler;
import com.beautycenter.management.domain.event.DomainEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple in-memory implementation of the DomainEventPublisher.
 * Publishes events to registered handlers.
 */
@Component
public class SimpleEventPublisher implements DomainEventPublisher {

    private static final Logger logger = LoggerFactory.getLogger(SimpleEventPublisher.class);

    private final Map<Class<? extends DomainEvent>, List<DomainEventHandler<? extends DomainEvent>>> handlers = new HashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public <T extends DomainEvent> void publish(T event) {
        logger.debug("Publishing event: {}", event.getClass().getSimpleName());
        
        List<DomainEventHandler<? extends DomainEvent>> eventHandlers = handlers.get(event.getClass());
        
        if (eventHandlers != null && !eventHandlers.isEmpty()) {
            for (DomainEventHandler handler : eventHandlers) {
                try {
                    logger.debug("Handling event with: {}", handler.getClass().getSimpleName());
                    handler.handle(event);
                } catch (Exception e) {
                    logger.error("Error handling event {} with handler {}: {}", 
                                 event.getClass().getSimpleName(),
                                 handler.getClass().getSimpleName(),
                                 e.getMessage(), e);
                }
            }
        } else {
            logger.debug("No handlers registered for event: {}", event.getClass().getSimpleName());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends DomainEvent> void subscribe(Class<T> eventType, DomainEventHandler<T> handler) {
        logger.debug("Subscribing handler {} to event type: {}", 
                    handler.getClass().getSimpleName(), 
                    eventType.getSimpleName());
        
        handlers.computeIfAbsent(eventType, k -> new ArrayList<>()).add(handler);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends DomainEvent> void unsubscribe(Class<T> eventType, DomainEventHandler<T> handler) {
        logger.debug("Unsubscribing handler {} from event type: {}", 
                    handler.getClass().getSimpleName(), 
                    eventType.getSimpleName());
        
        List<DomainEventHandler<? extends DomainEvent>> eventHandlers = handlers.get(eventType);
        
        if (eventHandlers != null) {
            eventHandlers.remove(handler);
            
            if (eventHandlers.isEmpty()) {
                handlers.remove(eventType);
            }
        }
    }
}