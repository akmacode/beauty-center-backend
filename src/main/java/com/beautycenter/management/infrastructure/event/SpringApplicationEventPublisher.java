package com.beautycenter.management.infrastructure.event;

import com.beautycenter.management.domain.event.DomainEvent;
import com.beautycenter.management.domain.event.DomainEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * Implementation of the DomainEventPublisher that uses Spring's ApplicationEventPublisher.
 * This class serves as an adapter between our domain events and Spring's event system.
 */
@Component
public class SpringApplicationEventPublisher implements DomainEventPublisher {
    
    private static final Logger logger = LoggerFactory.getLogger(SpringApplicationEventPublisher.class);
    
    private final ApplicationEventPublisher applicationEventPublisher;
    
    public SpringApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
    
    @Override
    public void publish(DomainEvent event) {
        logger.debug("Publishing domain event: {} ({})", event.getEventType(), event.getEventId());
        applicationEventPublisher.publishEvent(event);
    }
}