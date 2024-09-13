package com.wora.services;

import com.wora.models.dtos.CreateRouteDto;
import com.wora.models.entities.Graph;
import com.wora.models.entities.Route;
import com.wora.models.entities.Station;
import com.wora.repositories.IRouteRepository;
import com.wora.repositories.IStationRepository;
import com.wora.repositories.RouteRepository;

import java.sql.SQLException;
import java.util.*;

public class RouteService implements IRouteService{
    private final IRouteRepository repository;
    private final IStationRepository stationRepository;
    private final Graph graph;
    IRouteRepository routeRepository = new RouteRepository();
    private final Map<UUID, Station> stationMap = new HashMap<>();

    public RouteService(IRouteRepository repository, IStationRepository stationRepository) {
        this.repository = repository;
        this.stationRepository = stationRepository;
        this.graph = new Graph();
        
        initializeGraph();

    }

    private void initializeGraph() {
        List<Route> routes = routeRepository.findAll();
        List<Station> stations = stationRepository.findAll();

        for (Station station : stations) {
            graph.addStation(station);
        }

        for (Route route : routes) {
            graph.addRoute(route);
        }
    }

    @Override
    public List<UUID> findShortestPath(UUID startStationId, UUID endStationId) {
        return graph.findShortestPath(startStationId, endStationId);
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
    @Override
    public Route getRouteByStationIds(UUID departedId, UUID destinationId) {
        return repository.getRouteByStationIds(departedId, destinationId);
    }
}
