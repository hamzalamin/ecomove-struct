package com.wora.models.dtos;

import com.wora.models.entities.Route;
import com.wora.models.enums.TicketStatus;

import java.util.Date;
import java.util.UUID;

public record CreateTicketDto(
         Double purchasePrice,
         Double salePrice,
         Date saleDate,
         TicketStatus ticketStatus,
         UUID routeId
) {
}
