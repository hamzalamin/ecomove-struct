package com.wora.models.dtos;

import com.wora.models.enums.PartnerStatus;
import com.wora.models.enums.TransportType;

import java.util.Date;

public record CreatePartnerDto(
        String name,
        String contact,
        String geographicalZone,
        String specialCondition,
        PartnerStatus partnerStatus,
        TransportType transportType,
        Date creationDate
) {
}
