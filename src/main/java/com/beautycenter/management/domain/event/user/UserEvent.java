package com.beautycenter.management.domain.event.user;

import com.beautycenter.management.domain.event.AbstractDomainEvent;
import com.beautycenter.management.domain.model.User;

/**
 * Abstract base class for all user-related events.
 */
public abstract class UserEvent extends AbstractDomainEvent {
    
    private final User user;
    
    protected UserEvent(String eventType, User user) {
        super(eventType);
        this.user = user;
    }
    
    /**
     * Get the user associated with this event.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }
}