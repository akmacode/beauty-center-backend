package com.beautycenter.management.domain.event.user;

import com.beautycenter.management.domain.model.User;

/**
 * Event that is fired when a new user is created.
 */
public class UserCreatedEvent extends UserEvent {
    
    private static final String EVENT_TYPE = "user.created";
    
    /**
     * Create a new UserCreatedEvent.
     *
     * @param user the user that was created
     */
    public UserCreatedEvent(User user) {
        super(EVENT_TYPE, user);
    }
}