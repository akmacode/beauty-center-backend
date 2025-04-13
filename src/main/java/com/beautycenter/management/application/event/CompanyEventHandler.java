package com.beautycenter.management.application.event;

import com.beautycenter.management.domain.event.CompanyCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Event handler for company-related events
 */
@Component
public class CompanyEventHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(CompanyEventHandler.class);
    
    /**
     * Handle company creation events
     * 
     * @param event the created event
     */
    @EventListener
    @Async
    public void handleCompanyCreated(CompanyCreatedEvent event) {
        logger.info("Handling company creation: Company ID={}, Name={}, Owner ID={}",
                event.getCompanyId(), event.getCompanyName(), event.getOwnerId());
        
        // Implement notification or other side effects here
        // For example, setup default company settings
    }
}