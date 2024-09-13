package com.wora.presentation;

import com.wora.models.entities.AuthenticatedUser;
import com.wora.models.entities.User;
import com.wora.services.IBookingService;

import java.util.Scanner;

public class BookingUi {
    private final IBookingService bookingService;
    private final Scanner scanner = new Scanner(System.in);

    public BookingUi(IBookingService bookingService) {
        this.bookingService = bookingService;
    }

    private User getAuthenticatedUser() {
        if (AuthenticatedUser.isAuthentified()) {
            return AuthenticatedUser.getAuthenticatedUser();
        } else {
            System.out.println("User is not authenticated. Please log in.");
            return null;
        }
    }


}
