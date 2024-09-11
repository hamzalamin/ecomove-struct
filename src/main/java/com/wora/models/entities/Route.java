package com.wora.models.entities;

import java.util.UUID;

public class Route {
    private UUID id;
    private Station departed;
    private Station destination;
    private Double distance;

    public Route(UUID id, Station departed, Station destination, Double distance) {
        this.id = id;
        this.departed = departed;
        this.destination = destination;
        this.distance = distance;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Station getDeparted() {
        return departed;
    }

    public void setDeparted(Station departed) {
        this.departed = departed;
    }

    public Station getDestination() {
        return destination;
    }

    public void setDestination(Station destination) {
        this.destination = destination;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
