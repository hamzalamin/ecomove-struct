package com.wora.models.entities;

import com.wora.models.enums.ContractStatus;

import java.util.Date;
import java.util.UUID;

public class Contract {
    private UUID id;
    private Date startDate;
    private Date endDate;
    private Double specialCondition;
    private Boolean renewable;
    private ContractStatus contractStatus;
    private Partner partner;

    public Contract(UUID id, Date startDate, Date endDate, Double specialCondition, Boolean renewable, ContractStatus contractStatus, Partner partner) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.specialCondition = specialCondition;
        this.renewable = renewable;
        this.contractStatus = contractStatus;
        this.partner = partner;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Double getSpecialCondition() {
        return specialCondition;
    }

    public void setSpecialCondition(Double specialCondition) {
        this.specialCondition = specialCondition;
    }

    public Boolean getRenewable() {
        return renewable;
    }

    public void setRenewable(Boolean renewable) {
        this.renewable = renewable;
    }

    public ContractStatus getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(ContractStatus contractStatus) {
        this.contractStatus = contractStatus;
    }

    public Partner getPartner(){
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }
}
