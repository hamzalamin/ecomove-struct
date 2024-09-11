package com.wora.models.dtos;

import java.util.UUID;

public record CreateStationDto(
         String stationName,
         String city
) {
}
