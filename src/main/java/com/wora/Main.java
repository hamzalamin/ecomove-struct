package com.wora;

import com.wora.presentation.*;
import com.wora.repositories.*;
import com.wora.services.*;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        PartnerRepository PartnerRepository = new PartnerRepository();
        PartnerService partnerService = new PartnerService(PartnerRepository);
        PartnerUi partnerUi = new PartnerUi(partnerService);


        ContractRepository contractRepository = new ContractRepository();
        ContractService contractService = new ContractService(contractRepository);
        ContractUi contractUi = new ContractUi(contractService, partnerService);

        IUserRepository userRepository = new UserRepository();
        IUserService userService = new UserService(userRepository);

        UserUi userUi = new UserUi(userService);


        IOfferRepository offerRepository = new OfferRepository();
        IOfferService offerService = new OfferService(offerRepository);
        OfferUi offerUi = new OfferUi(offerService, contractService);

        ITicketRepository ticketRepository = new TicketRepository();
        ITicketService ticketService = new TicketService(ticketRepository);
        TicketUi ticketUi = new TicketUi(ticketService);

        IStationRepository stationRepository = new StationRepository();
        IStationService stationService = new StationService(stationRepository);
        StationUi stationUi = new StationUi(stationService);

        IBookingRepository bookingRepository = new BookingRepository();
        IBookingService bookingService = new BookingService(bookingRepository);
        BookingUi bookingUi = new BookingUi(bookingService);

        IRouteRepository routeRepository = new RouteRepository();
        IRouteService routeService = new RouteService(routeRepository, stationRepository);
        RouteUi routeUi = new RouteUi(routeService, stationService, ticketService, bookingService);



        MainMenu mainMenu = new MainMenu(partnerUi,contractUi,offerUi, ticketUi, userUi, stationUi, routeUi, bookingUi);
        userUi.setMainMenu(mainMenu);


        AuthUi authMenu = new AuthUi(userUi);
        authMenu.AuthMenu();
        userUi.login();
    }
}