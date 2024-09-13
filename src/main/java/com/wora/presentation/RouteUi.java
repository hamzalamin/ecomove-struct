package com.wora.presentation;

import com.wora.models.dtos.CreateRouteDto;
import com.wora.models.entities.Route;
import com.wora.models.entities.Station;
import com.wora.repositories.RouteRepository;
import com.wora.services.IRouteService;
import com.wora.services.IStationService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class RouteUi {
    private final IRouteService service;
    private final IStationService stationService;

    private final Scanner scanner = new Scanner(System.in);
    public RouteUi(IRouteService service, IStationService stationService) {
        this.service = service;
        this.stationService = stationService;
    }


    public void displayAllRoutes() {
        try {
            List<Route> routes = service.findAll();
            if (routes.isEmpty()) {
                System.out.println("No routes found");
                return;
            }

            for (int i = 0; i < routes.size(); i++) {
                Route route = routes.get(i);
                System.out.println((i + 1) + ". Route ID: " + route.getId() +
                        ", Departure Station ID: " + route.getDepartedId() +
                        ", Destination Station ID: " + route.getDestinationId() +
                        ", Distance: " + route.getDistance());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving routes", e);
        }
    }

    public void displayRouteById() {
        try {
            System.out.print("Enter the route ID: ");
            UUID routeId = UUID.fromString(scanner.next());

            Optional<Route> routeOpt = service.findById(routeId);
            if (routeOpt.isPresent()) {
                Route route = routeOpt.get();
                System.out.println("Route ID: " + route.getId() +
                        ", Departure Station ID: " + route.getDepartedId() +
                        ", Destination Station ID: " + route.getDestinationId() +
                        ", Distance: " + route.getDistance());
            } else {
                System.out.println("Route with ID " + routeId + " not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving route", e);
        }
    }


    public void create(){
        try {
            List<Station> stations = stationService.findAll();
            if (stations.isEmpty()) {
                System.out.println("No station found");
                return;
            }
            for (int i = 0; i < stations.size(); i++) {
                System.out.println((i + 1) + ". " + stations.get(i).getStationName() + " (ID: " + stations.get(i).getId() + ")");
            }

            System.out.print("Select the departure station by number: ");
            int departedIndex = scanner.nextInt() - 1;
            if (departedIndex < 0 || departedIndex >= stations.size()){
                System.out.println("invalide choice");
                return;
            }
            UUID departedId = stations.get(departedIndex).getId();

            System.out.print("Select the Destination station by number: ");
            int destinationIndex = scanner.nextInt() - 1;
            if (destinationIndex < 0 || destinationIndex >= stations.size()){
                System.out.println("invalide choice");
                return;
            }
            UUID destinationId = stations.get(destinationIndex).getId();

            System.out.println("Enter the distance between the stations");
            Double distance = scanner.nextDouble();

            System.out.println("Departure station ID: " + departedId);
            System.out.println("Destination station ID: " + destinationId);
            System.out.println("the Distance is: " + distance);

            CreateRouteDto dto = new CreateRouteDto(
                    departedId,
                    destinationId,
                    distance
            );
            service.create(dto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {
        try {
            List<Route> routes = service.findAll();
            if (routes.isEmpty()) {
                System.out.println("No routes found");
                return;
            }

            for (int i = 0; i < routes.size(); i++) {
                System.out.println((i + 1) + ". Route from station ID " + routes.get(i).getDepartedId() + " to station ID " + routes.get(i).getDestinationId() + " (Distance: " + routes.get(i).getDistance() + ")");
            }

            System.out.print("Select the route to update by number: ");
            int routeIndex = scanner.nextInt() - 1;
            if (routeIndex < 0 || routeIndex >= routes.size()) {
                System.out.println("Invalid choice");
                return;
            }

            Route routeToUpdate = routes.get(routeIndex);
            UUID routeId = routeToUpdate.getId();

            List<Station> stations = stationService.findAll();
            if (stations.isEmpty()) {
                System.out.println("No station found");
                return;
            }

            for (int i = 0; i < stations.size(); i++) {
                System.out.println((i + 1) + ". " + stations.get(i).getStationName() + " (ID: " + stations.get(i).getId() + ")");
            }

            System.out.print("Select the new departure station by number: ");
            int departedIndex = scanner.nextInt() - 1;
            if (departedIndex < 0 || departedIndex >= stations.size()) {
                System.out.println("Invalid choice");
                return;
            }
            UUID departedId = stations.get(departedIndex).getId();

            System.out.print("Select the new destination station by number: ");
            int destinationIndex = scanner.nextInt() - 1;
            if (destinationIndex < 0 || destinationIndex >= stations.size()) {
                System.out.println("Invalid choice");
                return;
            }
            UUID destinationId = stations.get(destinationIndex).getId();

            System.out.println("Enter the new distance between the stations");
            Double newDistance = scanner.nextDouble();

            System.out.println("Updating route with ID: " + routeId);
            CreateRouteDto dto = new CreateRouteDto(
                    departedId,
                    destinationId,
                    newDistance
            );
            service.update(dto, routeId);

            System.out.println("Route updated successfully.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void delete() {
        try {
            List<Route> routes = service.findAll();
            if (routes.isEmpty()) {
                System.out.println("No routes found");
                return;
            }

            for (int i = 0; i < routes.size(); i++) {
                System.out.println((i + 1) + ". Route from station ID " + routes.get(i).getDepartedId() + " to station ID " + routes.get(i).getDestinationId() + " (Distance: " + routes.get(i).getDistance() + ")");
            }

            System.out.print("Select the route to delete by number: ");
            int routeIndex = scanner.nextInt() - 1;
            if (routeIndex < 0 || routeIndex >= routes.size()) {
                System.out.println("Invalid choice");
                return;
            }

            UUID routeId = routes.get(routeIndex).getId();

            System.out.println("Deleting route with ID: " + routeId);
            service.delete(routeId);

            System.out.println("Route deleted successfully.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void start() {
        System.out.println("Welcome to the Route Finder!");

        List<Station> stations = stationService.findAll();
        if (stations.isEmpty()) {
            System.out.println("No station found");
            return;
        }
        for (int i = 0; i < stations.size(); i++) {
            System.out.println((i + 1) + ". " + stations.get(i).getStationName() + " (ID: " + stations.get(i).getId() + ")");
        }

        System.out.print("Enter the start station ID (UUID): ");
        int departedIndex = scanner.nextInt() - 1;
        if (departedIndex < 0 || departedIndex >= stations.size()){
            System.out.println("invalide choice");
            return;
        }
        UUID startStationId = stations.get(departedIndex).getId();


        System.out.print("Enter the end station ID (UUID): ");
        int destinationIndex = scanner.nextInt() - 1;
        if (destinationIndex < 0 || destinationIndex >= stations.size()){
            System.out.println("invalide choice");
            return;
        }        scanner.nextLine();
        UUID endStationId = stations.get(destinationIndex).getId();

        List<UUID> path = service.findShortestPath(startStationId, endStationId);
        if (path.isEmpty()) {
            System.out.println("No path found.");
        } else {
            System.out.println("Shortest path:");
            for (int i = 0; i < path.size() - 1; i++) {
                UUID departedId = path.get(i);
                UUID destinationId = path.get(i + 1);

                Route route = service.getRouteByStationIds(departedId, destinationId);

                System.out.println("Route from station " + departedId + " to station " + destinationId + ":");
                System.out.println("  Route ID: " + route.getId());
                System.out.println("  Distance: " + route.getDistance() + " km");
            }
        }
    }



}
