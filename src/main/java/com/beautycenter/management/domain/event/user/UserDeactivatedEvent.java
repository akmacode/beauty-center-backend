package com.beautycenter.management.domain.event.user;

import com.beautycenter.management.domain.model.User;

/**
 * Event that is fired when a user is deactivated.
 */
public class UserDeactivatedEvent extends UserEvent {
    
    private static final String EVENT_TYPE = "user.deactivated";
    
    /**
     * Create a new UserDeactivatedEvent.
     *
     * @param user the user that was deactivated
     */
    public UserDeactivatedEvent(User user) {
        super(EVENT_TYPE, user);
    }
}