package com.wora.services;

import com.wora.models.dtos.CreateStationDto;
import com.wora.models.entities.Station;
import com.wora.presentation.StationUi;
import com.wora.repositories.IStationRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class StationService implements IStationService {
    private final IStationRepository repository;

    public StationService(IStationRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Station> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Station> findById(UUID id) throws SQLException {
        return repository.findById(id);
    }

    @Override
    public Station create(CreateStationDto dto) throws SQLException {
        final Station station = new Station(dto.stationName(), dto.city());
        return repository.create(station);
    }


    @Override
    public Station update(CreateStationDto dto, UUID id) throws SQLException {
        Optional<Station> stationOptional = repository.findById(id);
        if (stationOptional.isPresent()) {
            Station station = stationOptional.get();
            station.setStationName(dto.stationName());
            station.setCity(dto.city());
            return repository.update(station, id);
        } else {
            throw new RuntimeException("Station with id " + id + " not found");
        }
    }

    @Override
    public void delete(String id) throws SQLException {
        Optional<Station> stationOptional = repository.findById(UUID.fromString(id));
        if (stationOptional.isPresent()) {
            repository.delete(UUID.fromString(id));
        } else {
            throw new RuntimeException("Station with id " + id + " not found");
        }
    }

}
