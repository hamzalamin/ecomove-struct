package com.wora.presentation;

import com.wora.models.dtos.CreateBookingDto;
import com.wora.models.entities.AuthenticatedUser;
import com.wora.models.entities.Ticket;
import com.wora.models.entities.User;
import com.wora.services.IBookingService;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class BookingUi {
    private final IBookingService service;
    private final Scanner scanner = new Scanner(System.in);

    public BookingUi(IBookingService service) {
        this.service = service;
    }

    private User getAuthenticatedUser() {
        if (AuthenticatedUser.isAuthentified()) {
            return AuthenticatedUser.getAuthenticatedUser();
        } else {
            System.out.println("User is not authenticated. Please log in.");
            return null;
        }
    }

    public void handleBookingFlow(List<Ticket> chosenTickets) {
        if (chosenTickets == null || chosenTickets.isEmpty()) {
            System.out.println("No tickets selected for booking.");
            return;
        }

        System.out.println("Proceeding with booking for the following tickets:");
        for (Ticket ticket : chosenTickets) {
            System.out.println("Ticket ID: " + ticket.getId() + ", Price: " + ticket.getSalePrice());
        }

        User user = getAuthenticatedUser();
        if (user == null) {
            return;
        }

        System.out.print("Do you want to book these tickets? (y/n): ");
        String answer = scanner.nextLine().trim().toLowerCase();

        if (answer.equals("y")) {
            CreateBookingDto dto = new CreateBookingDto(user);
            UUID bookingId = service.create(dto);

            for (Ticket ticket : chosenTickets) {
                service.addTicketToBooking(ticket.getId(), bookingId);
            }

            System.out.println("Booking successful with ID: " + bookingId);
        } else {
            System.out.println("Booking canceled.");
        }
    }



}



