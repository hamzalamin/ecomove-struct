package com.wora.presentation;
import com.wora.models.dtos.CreateRouteDto;
import com.wora.models.entities.Route;
import com.wora.models.entities.Station;
import com.wora.models.entities.Ticket;
import com.wora.services.IBookingService;
import com.wora.services.IRouteService;
import com.wora.services.IStationService;
import com.wora.services.ITicketService;

import java.util.*;

public class RouteUi {
    private final IRouteService service;
    private final IStationService stationService;
    private final ITicketService tService;
    private final IBookingService bService;
    private final Scanner scanner = new Scanner(System.in);

    public RouteUi(IRouteService service, IStationService stationService, ITicketService tService, IBookingService bService) {
        this.service = service;
        this.stationService = stationService;
        this.tService = tService;
        this.bService = bService;
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
                System.out.println((i + 1) + ". Route ID: " + route.getId() + ", Departure Station ID: " + route.getDepartedId() + ", Destination Station ID: " + route.getDestinationId() + ", Distance: " + route.getDistance());
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
                System.out.println("Route ID: " + route.getId() + ", Departure Station ID: " + route.getDepartedId() + ", Destination Station ID: " + route.getDestinationId() + ", Distance: " + route.getDistance());
            } else {
                System.out.println("Route with ID " + routeId + " not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving route", e);
        }
    }


    public void create() {
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
            if (departedIndex < 0 || departedIndex >= stations.size()) {
                System.out.println("invalide choice");
                return;
            }
            UUID departedId = stations.get(departedIndex).getId();

            System.out.print("Select the Destination station by number: ");
            int destinationIndex = scanner.nextInt() - 1;
            if (destinationIndex < 0 || destinationIndex >= stations.size()) {
                System.out.println("invalide choice");
                return;
            }
            UUID destinationId = stations.get(destinationIndex).getId();

            System.out.println("Enter the distance between the stations");
            Double distance = scanner.nextDouble();

            System.out.println("Departure station ID: " + departedId);
            System.out.println("Destination station ID: " + destinationId);
            System.out.println("the Distance is: " + distance);

            CreateRouteDto dto = new CreateRouteDto(departedId, destinationId, distance);
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
            CreateRouteDto dto = new CreateRouteDto(departedId, destinationId, newDistance);
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
            System.out.println("No stations found.");
            return;
        }
        for (int i = 0; i < stations.size(); i++) {
            System.out.println((i + 1) + ". " + stations.get(i).getStationName() + " (ID: " + stations.get(i).getId() + ")");
        }

        System.out.print("Enter the start station ID (UUID): ");
        int departedIndex = scanner.nextInt() - 1;
        if (departedIndex < 0 || departedIndex >= stations.size()) {
            System.out.println("Invalid choice.");
            return;
        }
        UUID startStationId = stations.get(departedIndex).getId();

        System.out.print("Enter the end station ID (UUID): ");
        int destinationIndex = scanner.nextInt() - 1;
        if (destinationIndex < 0 || destinationIndex >= stations.size()) {
            System.out.println("Invalid choice.");
            return;
        }
        scanner.nextLine();
        UUID endStationId = stations.get(destinationIndex).getId();

        List<UUID> path = service.findShortestPath(startStationId, endStationId);
        if (path.isEmpty()) {
            System.out.println("No path found.");
        } else {
            System.out.println("Shortest path:");
            List<Ticket> chosenTickets = new ArrayList<>();
            for (int i = 0; i < path.size() - 1; i++) {
                UUID departedId = path.get(i);
                UUID destinationId = path.get(i + 1);

                Route route = service.getRouteByStationIds(departedId, destinationId);
                List<Ticket> tickets = tService.ticketsByRouteId(route.getId());
                if (!tickets.isEmpty()) {
                    System.out.println("Route from station " + departedId + " to station " + destinationId + ":");
                    System.out.println("  Route ID: " + route.getId());
                    System.out.println("  Distance: " + route.getDistance() + " km");

                    if (!tickets.isEmpty()) {
                        System.out.println("Route from station " + departedId + " to station " + destinationId + ":");
                        System.out.println("  Route ID: " + route.getId());
                        System.out.println("  Distance: " + route.getDistance() + " km");

                        System.out.println("__________________________________________________");
                        System.out.println("Available Tickets for this route:");
                        System.out.println("__________________________________________________");

                        for (int j = 0; j < tickets.size(); j++) {
                            Ticket ticket = tickets.get(j);
                            System.out.println((j + 1) + ". Ticket ID: " + ticket.getId());
                            System.out.println("   Sale Price: " + ticket.getSalePrice());
                            System.out.println("   Sale Date: " + ticket.getSaleDate());
                            System.out.println("__________________________________________________");
                        }

                        System.out.print("Please select a ticket (1 - " + tickets.size() + "): ");
                        int selectedTicketIndex = scanner.nextInt() - 1;

                        if (selectedTicketIndex < 0 || selectedTicketIndex >= tickets.size()) {System.out.println("Invalid choice. Please try again.");
                            i--;
                            continue;
                        }

                        Ticket chosenTicket = tickets.get(selectedTicketIndex);
                        chosenTickets.add(chosenTicket);
                        System.out.println("You selected Ticket ID: " + chosenTicket.getId() + " for this route.");
                        System.out.println("__________________________________________________");

                    } else {
                        System.out.println("No tickets found for route from station " + departedId + " to station " + destinationId + ".");
                    }

                    System.out.println("You have selected the following tickets for your journey:");
                    for (Ticket ticket : chosenTickets) {
                        System.out.println("Ticket ID: " + ticket.getId() + ", Price: " + ticket.getSalePrice() + ", Sale Date: " + ticket.getSaleDate());
                    }


                } else {
                    System.out.println("no tickets found");
                }

                BookingUi bookingUi = new BookingUi(bService);
                bookingUi.handleBookingFlow(chosenTickets);

            }
        }
    }
}
