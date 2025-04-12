package com.beautycenter.management.domain.event.user;

import com.beautycenter.management.domain.model.User;

/**
 * Event that is fired when a user is deleted.
 */
public class UserDeletedEvent extends UserEvent {
    
    private static final String EVENT_TYPE = "user.deleted";
    
    /**
     * Create a new UserDeletedEvent.
     *
     * @param user the user that was deleted
     */
    public UserDeletedEvent(User user) {
        super(EVENT_TYPE, user);
    }
}