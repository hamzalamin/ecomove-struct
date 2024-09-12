package com.wora.models.entities;

import java.util.UUID;

public class Route {
    private UUID id;
    private UUID departedId;
    private UUID destinationId;
    private Double distance;

    public Route(UUID id, UUID departedId, UUID destinationId, Double distance) {
        this.id = id;
        this.departedId = departedId;
        this.destinationId = destinationId;
        this.distance = distance;
    }

    public Route(UUID departedId, UUID destinationId, Double distance) {
        this.departedId = departedId;
        this.destinationId = destinationId;
        this.distance = distance;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getDepartedId() {
        return departedId;
    }

    public void setDepartedId(UUID departedId) {
        this.departedId = this.departedId;
    }

    public UUID getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(UUID destinationId) {
        this.destinationId = destinationId;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
