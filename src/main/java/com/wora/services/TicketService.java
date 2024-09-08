package com.wora.services;

import com.wora.models.dtos.CreateTicketDto;
import com.wora.models.entities.Ticket;
import com.wora.repositories.TicketRepository;

import java.util.List;
import java.util.UUID;

public class TicketService {
    private final TicketRepository repository;

    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }

    public List<Ticket> findAll() {
        return repository.findAll();
    }

    public Ticket findById(String id) {
        return repository.findById(UUID.fromString(id))
                .orElseThrow();
    }

    public void create(CreateTicketDto dto) {
        repository.create(dto);
    }

    public void update(CreateTicketDto dto, UUID id) {
        repository.update(dto);
    }

    public void delete(String id) {
        repository.delete(UUID.fromString(id));
    }
}
