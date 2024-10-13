package com.wora.services;

import com.wora.models.dtos.CreateBookingDto;
import com.wora.models.dtos.CreateContractDto;
import com.wora.models.entities.Booking;
import com.wora.models.entities.Contract;
import com.wora.models.entities.Ticket;

import java.util.List;
import java.util.UUID;

public interface IBookingService {
    List<Booking> findAll();
    Booking findById(UUID id);
    UUID create(CreateBookingDto dto);
    void update(UUID id, CreateBookingDto dto);
    void delete(String id);
    void addTicketToBooking(UUID id, UUID bookingId);
}
