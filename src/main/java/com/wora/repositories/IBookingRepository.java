package com.wora.repositories;

import com.wora.models.dtos.CreateBookingDto;
import com.wora.models.entities.Booking;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IBookingRepository {
    List<Booking> findAll();
    Optional<Booking> findById(UUID id);
    void create(CreateBookingDto dto);
    void update(UUID id , CreateBookingDto dto);
    void delete(UUID id);
}
