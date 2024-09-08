package com.wora.services;

import com.wora.models.dtos.CreateContractDto;
import com.wora.models.entities.Contract;
import com.wora.repositories.ContractRepository;

import java.util.List;
import java.util.UUID;

public class ContractService {
    private final ContractRepository repository;

    public ContractService(ContractRepository repository) {
        this.repository = repository;
    }

    public List<Contract> findAll() {
        return repository.findAll();
    }

    public Contract findById(String id) {
        return repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("i'm here motherfucker"));
    }

    public void create(CreateContractDto dto) {
        repository.create(dto);
    }

    public void update(CreateContractDto dto, UUID id) {
        repository.update(dto, id);
    }

    public void delete(String id) {
        repository.delete(UUID.fromString(id));

    }


}
