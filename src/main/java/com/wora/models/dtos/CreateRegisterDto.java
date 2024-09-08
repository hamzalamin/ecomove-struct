package com.wora.models.dtos;

public record CreateRegisterDto(
        String name,
        String lastName,
        String email,
//        String password,
        String phoneNumber
) {
}
