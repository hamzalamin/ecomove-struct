package com.wora.presentation;

import com.wora.repositories.*;
import com.wora.services.*;

import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu {
    private final Scanner scanner = new Scanner(System.in);
    com.wora.repositories.PartnerRepository PartnerRepository = new PartnerRepository();
    PartnerService partnerService = new PartnerService(PartnerRepository);
    PartnerUi partnerUi = new PartnerUi(partnerService);

    ContractRepository contractRepository = new ContractRepository();
    ContractService contractService = new ContractService(contractRepository);
    ContractUi contractUi = new ContractUi(contractService, partnerService);

    OfferRepository offerRepository = new OfferRepository();
    OfferService offerService = new OfferService(offerRepository);
    OfferUi offerUi = new OfferUi(offerService, contractService);

    TicketRepository ticketRepository = new TicketRepository();
    TicketService ticketService = new TicketService(ticketRepository);
    TicketUi ticketUi = new TicketUi(ticketService);

    UserRepository userRepository = new UserRepository();
    UserService userService = new UserService(userRepository);
    UserUi userUi = new UserUi(userService);

    public MainMenu(PartnerUi partnerUi, ContractUi contractUi) {
        this.partnerUi = partnerUi;
        this.contractUi = contractUi;
    }

    public void showMenu() throws SQLException {
        int choice;
        do {
            System.out.println("Choose an action:");
            System.out.println("1. Create a Transport Partner");
            System.out.println("2. Update a Transport Partner");
            System.out.println("3. Delete a Transport Partner");
            System.out.println("4. View All Contracts");
            System.out.println("5. Create a Contract");
            System.out.println("6. Update a Contract");
            System.out.println("7. Delete a Contract");
            System.out.println("8. Create an Offer");
            System.out.println("9. Update an Offer");
            System.out.println("10. Delete an Offer");
            System.out.println("11. Create a Ticket");
            System.out.println("12. Update a Ticket");
            System.out.println("13. Delete a Ticket");
            System.out.println("14. Register");
            System.out.println("15. Log in");

            System.out.println("16. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    partnerUi.create();
                    break;
                case 2:
                    partnerUi.update();
                    break;
                case 3:
                    partnerUi.delete();
                    break;
                case 4:
                    contractUi.displayContracts();
                    break;
                case 5:
                    contractUi.create();
                    break;
                case 6:
                    contractUi.update();
                    break;
                case 7:
                    contractUi.delete();
                    break;
                case 8:
                    offerUi.creat();
                    break;
                case 9:
                    offerUi.update();
                    break;
                case 10:
                    offerUi.delete();
                    break;
                case 11:
                    ticketUi.create();
                    break;
                case 12:
                    ticketUi.update();
                    break;
                case 13:
                    ticketUi.delete();
                    break;
                case 14:
                    userUi.register();
                    break;
                case 15:
                    userUi.login();
                    break;
                case 16:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
            System.out.println();
        } while (choice != 16);
    }
}
