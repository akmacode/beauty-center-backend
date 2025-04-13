package com.beautycenter.management.domain.event.user;

import com.beautycenter.management.domain.model.User;

/**
 * Event that is fired when a user is updated.
 */
public class UserUpdatedEvent extends UserEvent {
    
    private static final String EVENT_TYPE = "user.updated";
    
    /**
     * Create a new UserUpdatedEvent.
     *
     * @param user the user that was updated
     */
    public UserUpdatedEvent(User user) {
        super(EVENT_TYPE, user);
    }
}