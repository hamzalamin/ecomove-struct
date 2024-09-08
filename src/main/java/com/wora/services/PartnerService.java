package com.wora.services;

import com.wora.exceptions.PartnerNotFoundException;
import com.wora.models.dtos.CreatePartnerDto;
import com.wora.models.entities.Partner;
import com.wora.repositories.PartnerRepository;

import java.util.List;
import java.util.UUID;

public class PartnerService {
    private final PartnerRepository repository;

    public PartnerService(PartnerRepository repository) {
        this.repository = repository;
    }

    public List<Partner> findAll() {
        return repository.findAll();
    }

    public Partner findById(String id) {
        return repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new PartnerNotFoundException(id));
    }

    public void create(CreatePartnerDto dto) {
        repository.create(dto);
    }

    public void update(CreatePartnerDto dto, UUID id) {
        repository.update(id,dto);
    }

    public void delete(String id) {
        repository.delete(UUID.fromString(id));
    }
}
