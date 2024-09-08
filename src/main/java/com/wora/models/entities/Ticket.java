package com.wora.models.entities;

import java.util.Date;
import java.util.UUID;

public class Ticket {
    private UUID id;
    private Double purchasePrice;
    private Double salePrice;
    private Date saleDate;

    public Ticket(UUID id, Double purchasePrice, Double salePrice, Date saleDate) {
        this.id = id;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.saleDate = saleDate;
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
}
