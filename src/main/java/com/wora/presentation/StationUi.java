package com.wora.presentation;

import com.wora.models.dtos.CreateStationDto;
import com.wora.models.entities.Station;
import com.wora.services.IStationService;

import java.util.List;
import java.util.Scanner;

public class StationUi {
    private final IStationService service;

    public StationUi(IStationService service) {
        this.service = service;
    }

    public void create() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter station name: ");
        String stationName = scanner.nextLine();

        System.out.print("Enter city: ");
        String city = scanner.nextLine();

        CreateStationDto dto = new CreateStationDto(
                stationName,
                city
        );
        try {
            Station createdStation = service.create(dto);
            System.out.println("-----------------------------------------------------");
            System.out.println("Station created successfully!");
            System.out.println("-----------------------------------------------------");
            System.out.println("Created Station ID: " + createdStation.getId());
            System.out.println("Station Name: " + createdStation.getStationName());
            System.out.println("City: " + createdStation.getCity());
            System.out.println("-----------------------------------------------------");

        } catch (Exception e) {
            System.out.println("Error creating station: " + e.getMessage());
        }
    }

    public void update(){
        Scanner scanner = new Scanner(System.in);
        try {
            List<Station> stations = service.findAll();
            if (stations.isEmpty()){
                System.out.println("no Stations found");
                return;
            }
            for (int station = 0 ; station < stations.size(); station++){
                System.out.println((station + 1) + " . " + stations.get(station).getStationName() + " (ID: "  + stations.get(station).getId() + ")");
            }
            System.out.println("Enter the number you want to update : ");
            int index = scanner.nextInt();
            if (index < 1 || index > stations.size()){
                System.out.println("Invalid selection.");
                return;
            }
            Station existStation = stations.get(index - 1);
            scanner.nextLine();
            System.out.print("Enter station name (or press to keep it same) : ");
            String stationName = scanner.nextLine();
            if (stationName != null){
                existStation.setStationName(stationName);
            }

            System.out.print("Enter city (or press to keep it same) : ");
            String city = scanner.nextLine();
            if (city != null){
                existStation.setCity(city);
            }

            CreateStationDto dto = new CreateStationDto(
                    stationName,
                    city
            );
            service.update(dto, existStation.getId());
            try {
                Station updatedStation = service.update(dto, existStation.getId());
                System.out.println("-----------------------------------------------------");
                System.out.println("Station created successfully!");
                System.out.println("-----------------------------------------------------");
                System.out.println("Created Station ID: " + updatedStation.getId());
                System.out.println("Station Name: " + updatedStation.getStationName());
                System.out.println("City: " + updatedStation.getCity());
                System.out.println("-----------------------------------------------------");

            } catch (Exception e) {
                System.out.println("Error creating station: " + e.getMessage());
            }
    } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void delete() {
        Scanner scanner = new Scanner(System.in);
        try {
            List<Station> stations = service.findAll();
            if (stations.isEmpty()){
                System.out.println("no Stations found");
                return;
            }
            for (int station = 0 ; station < stations.size(); station++){
                System.out.println((station + 1) + " . " + stations.get(station).getStationName() + " (ID: "  + stations.get(station).getId() + ")");
            }
            System.out.println("Enter the number you want to update : ");
            int index = scanner.nextInt();
            if (index < 1 || index > stations.size()){
                System.out.println("Invalid selection.");
                return;
            }
            Station existStation = stations.get(index - 1);

            if (existStation.getId() != null){
               service.delete(existStation.getId().toString());
                System.out.println("Station " + existStation.getStationName() + " DELETED SUCCESSFULLY ..." );
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
