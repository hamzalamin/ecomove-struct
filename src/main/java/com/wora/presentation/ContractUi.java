package com.wora.presentation;

import com.wora.models.dtos.CreateContractDto;
import com.wora.models.dtos.CreatePartnerDto;
import com.wora.models.entities.Contract;
import com.wora.models.entities.Offer;
import com.wora.models.entities.Partner;
import com.wora.models.enums.ContractStatus;
import com.wora.repositories.ContractRepository;
import com.wora.repositories.PartnerRepository;
import com.wora.services.ContractService;
import com.wora.services.IContractService;
import com.wora.services.IPartnerService;
import com.wora.services.PartnerService;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static com.wora.utli.InputScanner.scanDate;

public class ContractUi {

    private final IContractService service;
    private final IPartnerService partnerService;
    private final Scanner scanner = new Scanner(System.in);

    public ContractUi(IContractService service, IPartnerService partnerService) {
        this.service = service;
        this.partnerService = partnerService;
    }

    public void displayContracts() throws SQLException {

        List<Contract> contracts = service.findAll();
        if (contracts.isEmpty()) {
            System.out.println("No contracts found.");
            return;
        }

        String headerFormat = "| %-36s | %-10s | %-10s | %-15s | %-10s | %-15s |%n";
        String rowFormat = "| %-36s | %-10s | %-10s | %-15.2f | %-10s | %-15s |%n";

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf(headerFormat, "ID", "Start Date", "End Date", "Special Tariff", "Renewable", "Contract State");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");

        for (Contract contract : contracts) {
            System.out.printf(rowFormat,
                    contract.getId(),
                    contract.getStartDate(),
                    contract.getEndDate(),
                    contract.getSpecialCondition(),
                    contract.getRenewable() ? "Yes" : "No",
                    contract.getContractStatus()
            );
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
    }


    public void create() {
        Scanner scanner = new Scanner(System.in);

        try {
            Date startDate = scanDate("Enter start date:");
            Date endDate = scanDate("Enter end date:");

            System.out.println("Enter the special Condition: ");
            Double specialCondition = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Enter the renewable (True or False): ");
            Boolean renewable = scanner.nextBoolean();
            scanner.nextLine();

            System.out.println("Enter the contract state (ACTIVE, INACTIVE, SUSPENDED): ");
            ContractStatus contractStatus = ContractStatus.valueOf(scanner.nextLine().toUpperCase());

            System.out.println("Enter the Id of the transport partner: ");
            UUID partnerId = UUID.fromString(scanner.nextLine());
            Partner partner = partnerService.findById(partnerId.toString());

            if (partner != null) {
                CreateContractDto contractDTO = new CreateContractDto(startDate, endDate, specialCondition, renewable, contractStatus, partner);
                service.create(contractDTO);
            } else {
                System.out.println("Partner not found.");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error while creating contract: " + e.getMessage(), e);
        }
    }

    public void update() {
        try {
            List<Contract> contracts = service.findAll();
            if (contracts.isEmpty()) {
                System.out.println("no contract found");
                return;
            }
            for (int contract = 0; contract < contracts.size(); contract++) {
                System.out.println((contract + 1) + " . " + contracts.get(contract).getId() + " (ID: " + contracts.get(contract).getId() + ")");
            }
            System.out.println("Enter the number you want to update : ");
            int index = scanner.nextInt();

            if (index < 1 || index > contracts.size()) {
                System.out.println("Invalid selection.");
                return;
            }

            Contract existContract = contracts.get(index - 1);

            scanner.nextLine();
            System.out.println("Enter new special Condition (or press Enter to keep it the same):");
            Double specialCondition = scanner.nextDouble();
            if (specialCondition != null) {
                existContract.setSpecialCondition(specialCondition);
            }

            scanner.nextLine();
            Date startDate = scanDate("Enter start date (YYYY-MM-DD):");
            existContract.setStartDate(startDate != null ? startDate : new Date(System.currentTimeMillis()));

            Date endDate = scanDate("Enter end date (YYYY-MM-DD):");
            existContract.setEndDate(endDate != null ? endDate : new Date(System.currentTimeMillis()));

            System.out.println("Enter new renewable TRUE or FALSE (or press Enter to keep it the same):");
            Boolean renewable = scanner.nextBoolean();
            existContract.setRenewable(renewable);

            scanner.nextLine();
            System.out.print("Enter new Special Conditions: ACTIVE, INACTIVE, SUSPENDED (or press Enter to keep it the same): ");
            ContractStatus contractStatus = ContractStatus.valueOf(scanner.nextLine());
            if (contractStatus != null) {
                existContract.setContractStatus(contractStatus);
            }


            System.out.println("Enter the Id of the transport partner: ");
            UUID partnerId = UUID.fromString(scanner.nextLine());
            PartnerRepository repository = new PartnerRepository();
            PartnerService partnerService = new PartnerService(repository);
            Partner partner = partnerService.findById(partnerId.toString());
            if (partner != null) {
                existContract.setPartner(partner);
            }

            CreateContractDto dto = new CreateContractDto(
                    startDate,
                    endDate,
                    specialCondition,
                    renewable,
                    contractStatus,
                    partner
            );
            service.update(dto, existContract.getId());


            System.out.println("contract updated successfully.");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete() {
        try {
            List<Contract> contracts = service.findAll();
            if (contracts.isEmpty()) {
                System.out.println("no contract found");
                return;
            }
            for (int contract = 0; contract < contracts.size(); contract++) {
                System.out.println((contract + 1) + " . " + contracts.get(contract).getStartDate() + " (ID: " + contracts.get(contract).getId() + ")");
            }
            System.out.println("Enter the number you want to update : ");
            int index = scanner.nextInt();

            if (index < 1 || index > contracts.size()) {
                System.out.println("Invalid selection.");
                return;
            }
            Contract existContract = contracts.get(index - 1);

            if (existContract.getId() != null) {
                service.delete(existContract.getId().toString());
                System.out.println("Contract deleted successfully.");
            } else {
                System.out.println("Error: The selected Contract does not have a valid ID.");
            }
            System.out.println("contract Deleted successfully.");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
