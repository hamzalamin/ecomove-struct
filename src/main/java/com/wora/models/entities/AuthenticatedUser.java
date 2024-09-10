package com.wora.models.entities;

public class AuthenticatedUser {
    private static User user;

    public static User getAuthenticatedUser() {
        return user;
    }

    public static void setAuthenticatedUser(User user) {
        AuthenticatedUser.user = user;
    }

    public static Boolean isAuthentified() {
        return AuthenticatedUser.user != null;
    }

}
