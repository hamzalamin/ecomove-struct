package com.wora.services;

import com.wora.models.dtos.CreatePartnerDto;
import com.wora.models.entities.Partner;

import java.util.List;
import java.util.UUID;

public interface IPartnerService {

    List<Partner> findAll();
    Partner findById(String id);
    void create(CreatePartnerDto dto);
    void update(CreatePartnerDto dto, UUID id);
    void delete(String id);
}
