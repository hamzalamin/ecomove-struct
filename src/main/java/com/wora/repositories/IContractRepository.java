package com.wora.repositories;

import com.wora.models.dtos.CreateContractDto;
import com.wora.models.entities.Contract;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IContractRepository {
    List<Contract> findAll();
    Optional<Contract> findById(UUID id);
    void create(CreateContractDto dto);
    void update(CreateContractDto dto, UUID id);
    void delete(UUID id);

}
