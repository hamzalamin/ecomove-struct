package com.wora.models.dtos;

import com.wora.models.entities.Partner;

import java.util.Date;

public record CreateContractDto(
        Date startDate,
        Date endDate,
        Double specialCondition,
        Boolean renewable,
        com.wora.models.enums.ContractStatus contractStatus,
        Partner partner
) {

}
