package com.wora.models.dtos;

import com.wora.models.entities.User;

public record CreateBookingDto(
        User user
) {
}
