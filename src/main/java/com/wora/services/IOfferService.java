package com.wora.services;

import com.wora.models.dtos.CreateOfferDto;
import com.wora.models.entities.Offer;

import java.util.List;
import java.util.UUID;

public interface IOfferService {

    List<Offer> findAll();
    Offer findById(String id);
    void create(CreateOfferDto dto);
    void update(CreateOfferDto dto, UUID id);
    void delete(String id);
}
