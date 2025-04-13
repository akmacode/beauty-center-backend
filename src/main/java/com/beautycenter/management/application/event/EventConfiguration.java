package com.beautycenter.management.application.event;

import com.beautycenter.management.domain.event.EventPublisher;
import com.beautycenter.management.infrastructure.event.SimpleEventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for event handling
 */
@Configuration
public class EventConfiguration {
    
    /**
     * Create the event publisher bean
     * 
     * @param applicationEventPublisher Spring's event publisher
     * @return the domain event publisher
     */
    @Bean
    public EventPublisher eventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        return new SimpleEventPublisher(applicationEventPublisher);
    }
}