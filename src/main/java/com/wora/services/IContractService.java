package com.wora.services;

import com.wora.models.dtos.CreateContractDto;
import com.wora.models.entities.Contract;
import com.wora.repositories.ContractRepository;

import java.util.List;
import java.util.UUID;

public interface IContractService {
    List<Contract> findAll();
    Contract findById(String id);
    void create(CreateContractDto dto);
    void update(CreateContractDto dto, UUID id);
    void delete(String id);
}
