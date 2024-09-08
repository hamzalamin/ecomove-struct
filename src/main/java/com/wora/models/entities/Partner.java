package com.wora.models.entities;

import com.wora.models.enums.PartnerStatus;
import com.wora.models.enums.TransportType;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Partner {

    private UUID id;
    private String companyName;
    private String commercialContact;
    private String geographicalZone;
    private String specialCondition;
    private PartnerStatus partnerStatus;
    private TransportType transportType;
    private Date creationDate;
    private List<Contract> contracts;


    public Partner(UUID id, String companyName, String commercialContact, String geographicalZone, String specialCondition, PartnerStatus partnerStatus, TransportType transportType, Date creationDate, List<Contract> contracts) {
        this.id = id;
        this.companyName = companyName;
        this.commercialContact = commercialContact;
        this.geographicalZone = geographicalZone;
        this.specialCondition = specialCondition;
        this.partnerStatus = partnerStatus;
        this.transportType = transportType;
        this.creationDate = creationDate;
        this.contracts = contracts;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCommercialContact() {
        return commercialContact;
    }

    public void setCommercialContact(String commercialContact) {
        this.commercialContact = commercialContact;
    }

    public String getGeographicalZone() {
        return geographicalZone;
    }

    public void setGeographicalZone(String geographicalZone) {
        this.geographicalZone = geographicalZone;
    }

    public String getSpecialCondition() {
        return specialCondition;
    }

    public void setSpecialCondition(String specialCondition) {
        this.specialCondition = specialCondition;
    }

    public PartnerStatus getPartnerStatus() {
        return partnerStatus;
    }

    public void setPartnerStatus(PartnerStatus partnerStatus) {
        this.partnerStatus = partnerStatus;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }
}
