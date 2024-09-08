package com.wora.models.dtos;

import com.wora.models.entities.Contract;
import com.wora.models.enums.OfferStatus;
import com.wora.models.enums.ReductionType;

import java.util.Date;
import java.util.UUID;

public record CreateOfferDto(
        String offerName,
        String description,
        Date startDate,
        Date endDate,
        Double discountValue,
        String conditions,
        ReductionType reductionType,
        OfferStatus offerStatus,
        UUID contractId
        ) {

}
