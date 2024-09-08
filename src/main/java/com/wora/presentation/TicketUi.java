package com.wora.presentation;

import com.wora.models.dtos.CreateOfferDto;
import com.wora.models.dtos.CreateTicketDto;
import com.wora.models.entities.Ticket;
import com.wora.services.ContractService;
import com.wora.services.OfferService;
import com.wora.services.TicketService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static com.wora.utli.InputScanner.scanDate;

public class TicketUi {
    private final TicketService service;

    public TicketUi(TicketService service) {
        this.service = service;
    }

    public void create() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter the purchase Price");
            Double purchasePrice = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Enter the sale Price");
            Double salePrice = scanner.nextDouble();
            java.util.Date saleDate = scanDate("Enter start date (YYYY-MM-DD):");


            CreateTicketDto dto = new CreateTicketDto(
                    purchasePrice,
                    salePrice,
                    saleDate
            );
            service.create(dto);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void update() {
        Scanner scanner = new Scanner(System.in);
        try {
            List<Ticket> tickets = service.findAll();
            if (tickets.isEmpty()){
                System.out.println("no offers found");
                return;
            }
            for (int ticket = 0 ; ticket < tickets.size(); ticket++){
                System.out.println((ticket + 1) + " . " + tickets.get(ticket).getSaleDate() + " (ID: "  + tickets.get(ticket).getId() + ")");
            }
            System.out.println("Enter the number you want to update : ");
            int index = scanner.nextInt();
            if (index < 1 || index > tickets.size()){
                System.out.println("Invalid selection.");
                return;
            }
            Ticket existTicket = tickets.get(index - 1);
            scanner.nextLine();
            System.out.println("Enter new purchase Price (or press Enter to keep it the same):");
            Double purchasePrice = scanner.nextDouble();
            if (purchasePrice != null){
                existTicket.setPurchasePrice(purchasePrice);
            }
            scanner.nextLine();
            System.out.println("Enter new purchase Price (or press Enter to keep it the same):");
            Double salePrice = scanner.nextDouble();
            if (salePrice != null){
                existTicket.setSalePrice(salePrice);
            }
            scanner.nextLine();
            Date saleDate = scanDate("Enter start date (YYYY-MM-DD):");
            existTicket.setSaleDate(saleDate != null ? saleDate : new Date(System.currentTimeMillis()));
            if (saleDate != null){
                existTicket.setSaleDate(saleDate);
            }
            CreateTicketDto dto = new CreateTicketDto(
                    purchasePrice,
                    salePrice,
                    saleDate
            );
            service.update(dto, existTicket.getId());
            System.out.println("offre updated successfully.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete() {
        Scanner scanner = new Scanner(System.in);

        try {
            List<Ticket> tickets = service.findAll();
            if (tickets.isEmpty()){
                System.out.println("no offers found");
                return;
            }
            for (int ticket = 0 ; ticket < tickets.size(); ticket++){
                System.out.println((ticket + 1) + " . " + tickets.get(ticket).getSaleDate() + " (ID: "  + tickets.get(ticket).getId() + ")");
            }
            System.out.println("Enter the number you want to update : ");
            int index = scanner.nextInt();

            if (index < 1 || index > tickets.size()){
                System.out.println("Invalid selection.");
                return;
            }
            Ticket existTicket = tickets.get(index - 1);

            if (existTicket.getId() != null) {
                service.delete(existTicket.getId().toString());
                System.out.println("Contract deleted successfully.");
            } else {
                System.out.println("Error: The selected Contract does not have a valid ID.");
            }
            System.out.println("offre delete successfully.");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
