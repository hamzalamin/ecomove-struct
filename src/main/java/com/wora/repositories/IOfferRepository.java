package com.wora.repositories;

import com.wora.models.dtos.CreateOfferDto;
import com.wora.models.entities.Offer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IOfferRepository {

    List<Offer> findAll();
    Optional<Offer> findById(UUID id);
    void create(CreateOfferDto dto);
    void update(CreateOfferDto dto);
    void delete(UUID id);

}
