package com.wora.services;

import com.wora.models.dtos.CreateRouteDto;
import com.wora.models.entities.Route;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IRouteService {
    List<Route> findAll();
    Optional<Route> findById(UUID id) throws SQLException;
    Route create(CreateRouteDto dto) throws SQLException;
    Route update(CreateRouteDto dto, UUID id) throws SQLException;
    void delete(UUID id) throws SQLException;
}
