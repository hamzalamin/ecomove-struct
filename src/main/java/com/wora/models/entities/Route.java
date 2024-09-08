package com.wora.models.entities;

import java.sql.Time;
import java.util.UUID;

public class Route {
    private UUID id;
    private String departure;
    private String destination;
    private Time duration;
    private Ticket ticket;

    public Route(UUID id, String departure, String destination, Time duration, Ticket ticket) {
        this.id = id;
        this.departure = departure;
        this.destination = destination;
        this.duration = duration;
        this.ticket = ticket;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
