package com.wora.services;

import com.wora.models.dtos.CreateBookingDto;
import com.wora.models.entities.Booking;
import com.wora.repositories.IBookingRepository;

import java.util.List;
import java.util.UUID;

public class BookingService implements IBookingService {
    private final IBookingRepository repository;

    public BookingService(IBookingRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Booking> findAll() {
        return List.of();
    }

    @Override
    public Booking findById(UUID id) {
        return null;
    }

    @Override
    public UUID create(CreateBookingDto dto) {
        return repository.create(dto);
    }


    @Override
    public void update( UUID id, CreateBookingDto dto) {
        repository.update(id, dto);

    }

    @Override
    public void delete(String id) {
        repository.delete(UUID.fromString(id));
    }

    @Override
    public void addTicketToBooking(UUID ticketId, UUID bookingId) {
        repository.addTicketToBooking(ticketId, bookingId);
    }

}
