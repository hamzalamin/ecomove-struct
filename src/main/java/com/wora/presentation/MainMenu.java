package com.wora.presentation;

import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final PartnerUi partnerUi;
    private final ContractUi contractUi;
    private final OfferUi offerUi;
    private final TicketUi ticketUi;
    private final UserUi userUi;
    private final StationUi stationUi;
    private final RouteUi routeUi;
    private final BookingUi bookingUi;

    public MainMenu(PartnerUi partnerUi, ContractUi contractUi, OfferUi offerUi, TicketUi ticketUi, UserUi userUi, StationUi stationUi, RouteUi routeUi, BookingUi bookingUi) {
        this.partnerUi = partnerUi;
        this.contractUi = contractUi;
        this.offerUi = offerUi;
        this.ticketUi = ticketUi;
        this.userUi = userUi;
        this.stationUi = stationUi;
        this.routeUi = routeUi;
        this.bookingUi = bookingUi;
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
            System.out.println("14. Search for user ");
            System.out.println("15. Update my account");
            System.out.println("16. Create new station");
            System.out.println("17. Update station");
            System.out.println("18. Delete station");
            System.out.println("19. Create Route");
            System.out.println("20. Update Route");
            System.out.println("21. Delete Route");
            System.out.println("22. Display All Routes");
            System.out.println("23. Git Route By id");
            System.out.println("24. Search for Tickets");
            System.out.println("25. Booking");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> partnerUi.create();
                case 2 -> partnerUi.update();
                case 3 -> partnerUi.delete();
                case 4 -> contractUi.displayContracts();
                case 5 -> contractUi.create();
                case 6 -> contractUi.update();
                case 7 -> contractUi.delete();
                case 8 -> offerUi.create();
                case 9 -> offerUi.update();
                case 10 -> offerUi.delete();
                case 11 -> ticketUi.create();
                case 12 -> ticketUi.update();
                case 13 -> ticketUi.delete();
                case 14 -> userUi.search();
                case 15 -> userUi.update();
                case 16 -> stationUi.create();
                case 17 -> stationUi.update();
                case 18 ->stationUi.delete();
                case 19 ->routeUi.create();
                case 20 ->routeUi.update();
                case 21 ->routeUi.delete();
                case 22 ->routeUi.displayAllRoutes();
                case 23 ->routeUi.displayRouteById();
                case 24 ->routeUi.start();
//                case 25 ->bookingUi.create();

                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        } while (choice != 0);
    }
}
