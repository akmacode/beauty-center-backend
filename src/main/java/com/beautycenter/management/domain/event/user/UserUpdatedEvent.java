package com.beautycenter.management.domain.event.user;

import com.beautycenter.management.domain.event.AbstractDomainEvent;
import com.beautycenter.management.domain.model.User;

import java.time.Instant;
import java.util.UUID;

/**
 * Domain event that is published when a user is updated.
 */
public class UserUpdatedEvent extends AbstractDomainEvent {

    private final UUID userId;
    private final String username;
    private final String email;
    private final String fullName;

    /**
     * Constructs a new UserUpdatedEvent.
     *
     * @param user the user that was updated
     */
    public UserUpdatedEvent(User user) {
        super();
        this.userId = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.fullName = user.getFullName();
    }

    /**
     * Constructs a new UserUpdatedEvent with specified ID and timestamp.
     *
     * @param eventId    the event ID
     * @param occurredAt the timestamp when the event occurred
     * @param userId     the ID of the user that was updated
     * @param username   the username of the user that was updated
     * @param email      the email of the user that was updated
     * @param fullName   the full name of the user that was updated
     */
    public UserUpdatedEvent(UUID eventId, Instant occurredAt, UUID userId, String username, String email, String fullName) {
        super(eventId, occurredAt);
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.fullName = fullName;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }
}