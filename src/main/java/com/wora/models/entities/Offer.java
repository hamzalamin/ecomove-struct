package com.wora.models.entities;

import com.wora.models.enums.OfferStatus;
import com.wora.models.enums.ReductionType;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Offer {
    private UUID id;
    private String offerName;
    private String description;
    private Date startDate;
    private Date endDate;
    private Double discountValue;
    private String condition;
    private ReductionType reductionType;
    private OfferStatus offerStatus;
    private Contract contract;

    public Offer(UUID id, String offerName, String description, Date startDate, Date endDate, Double discountValue, String condition, ReductionType reductionType, OfferStatus offerStatus, Contract contract) {
        this.id = id;
        this.offerName = offerName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountValue = discountValue;
        this.condition = condition;
        this.reductionType = reductionType;
        this.offerStatus = offerStatus;
        this.contract = contract;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Double discountValue) {
        this.discountValue = discountValue;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public ReductionType getReductionType() {
        return reductionType;
    }

    public void setReductionType(ReductionType reductionType) {
        this.reductionType = reductionType;
    }

    public OfferStatus getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(OfferStatus offerStatus) {
        this.offerStatus = offerStatus;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
