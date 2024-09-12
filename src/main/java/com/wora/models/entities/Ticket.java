package com.wora.models.entities;

import com.wora.models.enums.TicketStatus;

import java.util.Date;
import java.util.UUID;

public class Ticket {
    private UUID id;
    private Double purchasePrice;
    private Double salePrice;
    private Date saleDate;
    private TicketStatus ticketStatus;
    private Route routeId;

    public Ticket(UUID id, Double purchasePrice, Double salePrice, Date saleDate, TicketStatus ticketStatus, Route routeId) {
        this.id = id;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.saleDate = saleDate;
        this.ticketStatus = ticketStatus;
        this.routeId = routeId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public Route getRouteId() {
        return routeId;
    }

    public void setRouteId(Route routeId) {
        this.routeId = routeId;
    }
}
