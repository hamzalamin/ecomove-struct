package com.wora.models.dtos;

import java.util.Date;
import java.util.UUID;

public record CreateTicketDto(
         Double purchasePrice,
         Double salePrice,
         Date saleDate
) {
}
