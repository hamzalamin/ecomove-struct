package com.wora.services;

import com.wora.exceptions.PartnerNotFoundException;
import com.wora.models.dtos.CreatePartnerDto;
import com.wora.models.entities.Partner;
import com.wora.repositories.PartnerRepository;

import java.util.List;
import java.util.UUID;

public class PartnerService implements IPartnerService{
    private final PartnerRepository repository;

    public PartnerService(PartnerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Partner> findAll() {
        return repository.findAll();
    }

    @Override
    public Partner findById(String id) {
        return repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new PartnerNotFoundException(id));
    }

    @Override
    public void create(CreatePartnerDto dto) {
        repository.create(dto);
    }

    @Override
    public void update(CreatePartnerDto dto, UUID id) {
        repository.update(id,dto);
    }

    @Override
    public void delete(String id) {
        repository.delete(UUID.fromString(id));
    }
}
