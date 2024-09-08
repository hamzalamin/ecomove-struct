package com.wora.services;

import com.wora.models.dtos.CreateOfferDto;
import com.wora.models.entities.Offer;
import com.wora.repositories.OfferRepository;

import java.util.List;
import java.util.UUID;

public class OfferService {
    private final OfferRepository repository;

    public OfferService(OfferRepository repository) {
        this.repository = repository;
    }
    public List<Offer> findAll(){
        return repository.findAll();
    }
    public Offer findById(String id){
        return  repository.findById(UUID.fromString(id))
                .orElseThrow();
    }
    public void create(CreateOfferDto dto){
        repository.create(dto);
    }

    public void update(CreateOfferDto dto, UUID id){
        repository.update(dto);
    }

    public void delete(String id){
        repository.delete(UUID.fromString(id));
    }
}
