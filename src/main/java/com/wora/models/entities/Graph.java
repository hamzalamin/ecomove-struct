package com.wora.models.entities;

import java.util.*;

public class Graph {

    private Map<UUID, List<Route>> adjacencyList = new HashMap<>();
    private Map<UUID, Station> stations = new HashMap<>();

    public void addStation(Station station) {
        adjacencyList.putIfAbsent(station.getId(), new ArrayList<>());
        stations.put(station.getId(), station);
    }

    public void addRoute(Route route) {
        adjacencyList.computeIfAbsent(route.getDepartedId(), k -> new ArrayList<>()).add(route);
    }

    public List<UUID> findShortestPath(UUID startId, UUID endId) {
        Map<UUID, Double> distances = new HashMap<>();
        Map<UUID, UUID> previous = new HashMap<>();
        PriorityQueue<UUID> nodes = new PriorityQueue<>(Comparator.comparing(distances::get));
        List<UUID> path = new ArrayList<>();

        for (UUID stationId : adjacencyList.keySet()) {
            distances.put(stationId, Double.MAX_VALUE);
            previous.put(stationId, null);
        }
        distances.put(startId, 0.0);
        nodes.add(startId);

        while (!nodes.isEmpty()) {
            UUID current = nodes.poll();

            if (current.equals(endId)) {
                while (previous.get(current) != null) {
                    path.add(0, current);
                    current = previous.get(current);
                }
                path.add(0, startId);
                return path;
            }

            for (Route route : adjacencyList.getOrDefault(current, Collections.emptyList())) {
                UUID neighborId = route.getDestinationId();
                double newDist = distances.get(current) + route.getDistance();
                if (newDist < distances.get(neighborId)) {
                    distances.put(neighborId, newDist);
                    previous.put(neighborId, current);
                    nodes.add(neighborId);
                }
            }
        }
        return path;
    }
    public String getStationDetails(UUID stationId) {
        Station station = stations.get(stationId);
        if (station != null) {
            return "Station: " + station.getStationName() + ", City: " + station.getCity();
        }
        return "Unknown Station";
    }
}
