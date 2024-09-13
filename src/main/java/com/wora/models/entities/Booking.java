package com.wora.models.entities;

import java.util.UUID;

public class Booking {
    private UUID id;
    private User user;

    public Booking(UUID id, User user) {
        this.id = id;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
