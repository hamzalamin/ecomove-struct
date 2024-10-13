package com.wora.services;

import com.wora.models.dtos.CreateTicketDto;
import com.wora.models.entities.Ticket;

import java.util.List;
import java.util.UUID;

public interface ITicketService {

    List<Ticket> findAll();
    Ticket findById(String id);
    void create(CreateTicketDto dto);
    void update(CreateTicketDto dto, UUID id);
    void delete(String id);
    List<Ticket> ticketsByRouteId(UUID routeId);
}
