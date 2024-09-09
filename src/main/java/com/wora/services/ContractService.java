package com.wora.services;

import com.wora.models.dtos.CreateContractDto;
import com.wora.models.entities.Contract;
import com.wora.repositories.ContractRepository;

import java.util.List;
import java.util.UUID;

public class ContractService implements IContractService {
    private final ContractRepository repository;

    public ContractService(ContractRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Contract> findAll() {
        return repository.findAll();
    }

    @Override
    public Contract findById(String id) {
        return repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("i'm here motherfucker"));
    }

    @Override
    public void create(CreateContractDto dto) {
        repository.create(dto);
    }

    @Override
    public void update(CreateContractDto dto, UUID id) {
        repository.update(dto, id);
    }

    @Override
    public void delete(String id) {
        repository.delete(UUID.fromString(id));

    }


}
