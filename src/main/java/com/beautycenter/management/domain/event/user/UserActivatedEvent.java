package com.beautycenter.management.domain.event.user;

import com.beautycenter.management.domain.model.User;

/**
 * Event that is fired when a user is activated.
 */
public class UserActivatedEvent extends UserEvent {
    
    private static final String EVENT_TYPE = "user.activated";
    
    /**
     * Create a new UserActivatedEvent.
     *
     * @param user the user that was activated
     */
    public UserActivatedEvent(User user) {
        super(EVENT_TYPE, user);
    }
}