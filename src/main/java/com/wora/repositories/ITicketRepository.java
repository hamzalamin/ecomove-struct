package com.wora.repositories;

import com.wora.models.dtos.CreateTicketDto;
import com.wora.models.entities.Ticket;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ITicketRepository {
    List<Ticket> findAll();
    Optional<Ticket> findById(UUID id);
    void create(CreateTicketDto dto);
    void update(CreateTicketDto dto);
    void delete(UUID id);
    void addTicketToBooking(UUID ticketId, UUID bookingId);
    List<Ticket> getTicketsByRouteId(UUID routeId);

}
