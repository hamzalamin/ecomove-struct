package com.wora.presentation;

import java.sql.SQLException;
import java.util.Scanner;

public class AuthUi {
    private final UserUi userUi;
    private static final Scanner scanner = new Scanner(System.in);

    public AuthUi(UserUi userUi) {
        this.userUi = userUi;
    }


    public void AuthMenu() throws SQLException {
        int choice = 0;

        do {
            System.out.println("1. Register");
            System.out.println("2. Log in");
            System.out.println("3. Exit");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    userUi.register();
                    break;
                case 2:
                    userUi.login();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 3);
    }
}
