package com.wora.services;

import com.wora.models.dtos.CreateOfferDto;
import com.wora.models.entities.Offer;
import com.wora.repositories.IOfferRepository;
import com.wora.repositories.OfferRepository;

import java.util.List;
import java.util.UUID;

public class OfferService implements IOfferService {
    private final IOfferRepository repository;

    public OfferService(IOfferRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Offer> findAll(){
        return repository.findAll();
    }

    @Override
    public Offer findById(String id){
        return  repository.findById(UUID.fromString(id))
                .orElseThrow();
    }

    @Override
    public void create(CreateOfferDto dto){
        repository.create(dto);
    }

    @Override
    public void update(CreateOfferDto dto, UUID id){
        repository.update(dto);
    }

    @Override
    public void delete(String id){
        repository.delete(UUID.fromString(id));
    }
}
