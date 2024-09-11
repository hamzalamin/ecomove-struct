package com.wora.services;

import com.wora.models.dtos.CreateStationDto;
import com.wora.models.entities.Station;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IStationService {
    List<Station> findAll();
    Optional<Station> findById(UUID id) throws SQLException;
    Station create(CreateStationDto dto) throws SQLException;
    Station update(CreateStationDto dto, UUID id) throws SQLException;
    void delete(String id) throws SQLException;
}
