package com.wora.models.entities;

import java.util.UUID;

public class User {
    private UUID id;
    private String name;
    private String lastName;
    private String email;
    private String phone_Number;

    public User(UUID id, String name, String lastName, String email, String phone_Number) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone_Number = phone_Number;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_Number() {
        return phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        this.phone_Number = phone_Number;
    }
}
