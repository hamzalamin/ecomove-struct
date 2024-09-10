package com.wora.presentation;

import com.wora.models.dtos.CreateLoginDto;
import com.wora.models.dtos.CreateRegisterDto;
import com.wora.models.entities.User;
import com.wora.services.IUserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static com.wora.utli.InputScanner.scanner;

public class UserUi {
    private final IUserService Service;
    private MainMenu mainMenu;

    public UserUi(IUserService service) {
        Service = service;

    }

    public void setMainMenu(MainMenu mainMenu){
        this.mainMenu = mainMenu;
    }

    public void register() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your first name: ");
        String name = scanner.nextLine();

        System.out.println("Enter your last name: ");
        String lastName = scanner.nextLine();

        System.out.println("Enter your email: ");
        String email = scanner.nextLine();

        System.out.println("Enter your phone number: ");
        String phoneNumber = scanner.nextLine();

        CreateRegisterDto dto = new CreateRegisterDto(name, lastName, email, phoneNumber);
        Service.registerUser(dto);

        System.out.println("User registered successfully!");
    }

    public void login() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your email: ");
        String email = scanner.nextLine();

        System.out.println("Enter your name: ");
        String name = scanner.nextLine();

        Optional<User> user = Service.loginUser(new CreateLoginDto(email, name));

        if (user.isPresent()) {
            System.out.println("Login successful! Welcome, " + user.get().getName());
            mainMenu.showMenu();
        } else {
            System.out.println("Invalid email or password. Please try again.");
        }
    }

    public void search() {
        System.out.println("Search Users");
        System.out.print("Enter email (or leave empty): ");
        String email = scanner.nextLine().trim();

        System.out.print("Enter name (or leave empty): ");
        String name = scanner.nextLine().trim();

        try {
            List<User> users = Service.searchUsers(email, name);
            if (users.isEmpty()) {
                System.out.println("No users found.");
            } else {
                System.out.println("Search Results:");
                for (User user : users) {
                    System.out.println("ID: " + user.getId() +
                            ", Name: " + user.getName() +
                            ", Last Name: " + user.getLastName() +
                            ", Email: " + user.getEmail() +
                            ", Phone Number: " + user.getPhone_Number());
                }
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while searching for users: " + e.getMessage());
        }
    }
}
