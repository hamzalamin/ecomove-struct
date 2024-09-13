package com.wora.repositories;
import com.wora.models.entities.Route;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IRouteRepository {
    List<Route> findAll();
    Optional<Route> findById(UUID id) throws SQLException;
    Route create(Route route) throws SQLException;
    Route update(Route route, UUID id) throws SQLException;
    void delete(UUID id);
    Route getRouteByStationIds(UUID departedId, UUID destinationId);
}
