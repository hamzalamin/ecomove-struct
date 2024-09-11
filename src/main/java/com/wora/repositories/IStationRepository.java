package com.wora.repositories;

import com.wora.models.entities.Station;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IStationRepository {
    List<Station>findAll();
    Optional<Station>findById(UUID id) throws SQLException;
    Station create(Station station) throws SQLException;
    Station update(Station station,  UUID id) throws SQLException;
    void delete(UUID id);
}
