package com.wora.models.dtos;

import java.util.UUID;

public record CreateRouteDto(
        UUID departedId,
        UUID destinationId,
        Double distance
) {
}
