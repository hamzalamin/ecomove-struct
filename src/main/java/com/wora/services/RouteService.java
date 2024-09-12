package com.wora.services;

import com.wora.models.dtos.CreateRouteDto;
import com.wora.models.entities.Route;
import com.wora.repositories.IRouteRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RouteService implements IRouteService{
    private final IRouteRepository repository;

    public RouteService(IRouteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Route> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Route> findById(UUID id) throws SQLException {
        return repository.findById(id);
    }

    @Override
    public Route create(CreateRouteDto dto) throws SQLException {
        final Route route = new Route(UUID.randomUUID(), dto.departedId(), dto.destinationId(), dto.distance());
        return repository.create(route);
    }

    @Override
    public Route update(CreateRouteDto dto, UUID id) throws SQLException {
        Optional<Route> routeOptional = repository.findById(id);
        if (routeOptional.isPresent()){
            Route route = routeOptional.get();
            route.setDepartedId(dto.departedId());
            route.setDestinationId(dto.destinationId());
            route.setDistance(dto.distance());
        return repository.update(route, id);
        } else {
            throw new RuntimeException("the route with id " + id +" is not found");
        }
    }

    @Override
    public void delete(UUID id) throws SQLException {
        Optional<Route> routeOptional = repository.findById(id);
        if (routeOptional.isPresent()){
            repository.delete(id);
        } else {
            throw new RuntimeException("the route with id " + id +" is not found");
        }
    }
}
