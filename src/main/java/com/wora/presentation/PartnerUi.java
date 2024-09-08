package com.wora.presentation;

import com.wora.models.dtos.CreatePartnerDto;
import com.wora.models.entities.Partner;
import com.wora.models.enums.PartnerStatus;
import com.wora.models.enums.TransportType;
import com.wora.services.PartnerService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PartnerUi {
    private final PartnerService service;
    private final Scanner scanner = new Scanner(System.in);

    public PartnerUi(PartnerService partnerService) {
        this.service = partnerService;
    }

    public void create() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter the Company Name:");
            String companyName = scanner.nextLine();
            while (companyName.isEmpty()) {
                System.out.println("You Please enter the Company Name:");
                companyName = scanner.nextLine();
            }


            System.out.println("Enter Comercial Contact:");
            String contact = scanner.nextLine();

            System.out.println("Enter the Geographic Zone:");
            String geographicZone = scanner.nextLine();

            System.out.println("Enter Special Conditions:");
            String specialConditions = scanner.nextLine();

            Date creationDate = new Date();
            ;


            PartnerStatus partnerStatus = null;
            while (partnerStatus == null) {
                try {
                    System.out.print("Enter Partner State (ACTIVE, INACTIVE, SUSPENDED): ");
                    partnerStatus = PartnerStatus.valueOf(scanner.nextLine().toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid input. Please enter a valid Partner State (ACTIVE, INACTIVE, SUSPENDED).");
                }
            }

            TransportType transportType = null;
            while (transportType == null) {
                try {
                    System.out.print("Enter Transport Type (PLANE, BUS, TRAIN): ");
                    transportType = TransportType.valueOf(scanner.nextLine().toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid input. Please enter a valid Transport Type (PLANE, BUS, TRAIN).");
                }
            }
            CreatePartnerDto dto = new CreatePartnerDto(
                    companyName,
                    contact,
                    geographicZone,
                    specialConditions,
                    partnerStatus,
                    transportType,
                    creationDate
            );
            service.create(dto);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void update() {
        try {

            List<Partner> partners = service.findAll();
            if (partners.isEmpty()) {
                System.out.println("No TransportPartners found.");
                return;
            }

            for (int i = 0; i < partners.size(); i++) {
                System.out.println((i + 1) + ". " + partners.get(i).getCompanyName() + " (ID: " + partners.get(i).getId() + ")");
            }

            System.out.print("Enter the number of the TransportPartner you want to update: ");
            int index = scanner.nextInt();
            scanner.nextLine();

            if (index < 1 || index > partners.size()) {
                System.out.println("Invalid selection.");
                return;
            }

            Partner existingTransportPartner = partners.get(index - 1);

            System.out.println("Enter new Company Name (or press Enter to keep it the same):");
            String companyName = scanner.nextLine();
            if (!companyName.isEmpty()) {
                existingTransportPartner.setCompanyName(companyName);
            }

            System.out.print("Enter new Contact (or press Enter to keep it the same): ");
            String contact = scanner.nextLine();
            if (!contact.isEmpty()) {
                existingTransportPartner.setCommercialContact(contact);
            }

            System.out.print("Enter new Geographic Zone (or press Enter to keep it the same): ");
            String geographicZone = scanner.nextLine();
            if (!geographicZone.isEmpty()) {
                existingTransportPartner.setGeographicalZone(geographicZone);
            }

            System.out.print("Enter new Special Conditions (or press Enter to keep it the same): ");
            String specialConditions = scanner.nextLine();
            if (!specialConditions.isEmpty()) {
                existingTransportPartner.setSpecialCondition(specialConditions);
            }

            Date creationDate = new Date();

            System.out.print("Enter new Partner States (or press Enter to keep it the same): ");
            PartnerStatus partnerStatus = PartnerStatus.valueOf(scanner.nextLine());
            if (partnerStatus != null) {
                existingTransportPartner.setPartnerStatus(partnerStatus);
            }

            System.out.print("Enter new Transport Type (or press Enter to keep it the same): ");
            TransportType transportType = TransportType.valueOf(scanner.nextLine());
            if (transportType != null) {
                existingTransportPartner.setTransportType(transportType);
            }
            CreatePartnerDto dto = new CreatePartnerDto(
                    companyName,
                    contact,
                    geographicZone,
                    specialConditions,
                    partnerStatus,
                    transportType,
                    creationDate
            );
            service.update(dto, existingTransportPartner.getId());

            System.out.println("TransportPartner updated successfully.");

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void delete() {
        try {
            List<Partner> partners = service.findAll();
            if (partners.isEmpty()) {
                System.out.println("No TransportPartners found.");
                return;
            }

            for (int i = 0; i < partners.size(); i++) {
                System.out.println((i + 1) + ". " + partners.get(i).getCompanyName() + " (ID: " + partners.get(i).getId() + ")");
            }

            System.out.print("Enter the number of the TransportPartner you want to delete: ");
            int index = scanner.nextInt();
            scanner.nextLine();

            if (index < 1 || index > partners.size()) {
                System.out.println("Invalid selection.");
                return;
            }

            Partner existingTransportPartner = partners.get(index - 1);

            if (existingTransportPartner.getId() != null) {
                service.delete(existingTransportPartner.getId().toString());
                System.out.println("TransportPartner deleted successfully.");
            } else {
                System.out.println("Error: The selected TransportPartner does not have a valid ID.");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error Deleting TransportPartner: " + e.getMessage(), e);
        }
    }
}
