package com.wora.repositories;

import com.wora.models.dtos.CreatePartnerDto;
import com.wora.models.entities.Partner;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPartnerRepository {
    List<Partner> findAll();
    Optional<Partner> findById(UUID id);
    void create(CreatePartnerDto dto);
    void update(UUID id, CreatePartnerDto dto);
    void delete(UUID id);
}
