package com.wora.presentation;

import com.wora.models.dtos.CreateLoginDto;
import com.wora.models.dtos.CreateRegisterDto;
import com.wora.models.entities.User;
import com.wora.services.UserService;

import java.util.Optional;
import java.util.Scanner;

public class UserUi {
    private final UserService Service;

    public UserUi(UserService service) {
        Service = service;
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

    public void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your email: ");
        String email = scanner.nextLine();

        System.out.println("Enter your name: ");
        String name = scanner.nextLine();

        Optional<User> user = Service.loginUser(new CreateLoginDto(email, name));

        if (user.isPresent()) {
            System.out.println("Login successful! Welcome, " + user.get().getName());
        } else {
            System.out.println("Invalid email or password. Please try again.");
        }
    }
}
