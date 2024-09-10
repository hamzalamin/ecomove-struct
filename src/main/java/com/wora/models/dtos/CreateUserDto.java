package com.wora.models.dtos;

public record CreateUserDto(
        String name,
        String email,
        String lastName,
        String phoneNumber
) {
}
