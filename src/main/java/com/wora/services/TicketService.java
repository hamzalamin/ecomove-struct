package com.wora.services;

import com.wora.models.dtos.CreateTicketDto;
import com.wora.models.entities.Ticket;
import com.wora.repositories.ITicketRepository;
import com.wora.repositories.TicketRepository;

import java.util.List;
import java.util.UUID;

public class TicketService implements ITicketService {
    private final ITicketRepository repository;

    public TicketService(ITicketRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Ticket> findAll() {
        return repository.findAll();
    }

    @Override
    public Ticket findById(String id) {
        return repository.findById(UUID.fromString(id))
                .orElseThrow();
    }

    @Override
    public void create(CreateTicketDto dto) {
        repository.create(dto);
    }

    @Override
    public void update(CreateTicketDto dto, UUID id) {
        repository.update(dto);
    }

    @Override
    public void delete(String id) {
        repository.delete(UUID.fromString(id));
    }
}
