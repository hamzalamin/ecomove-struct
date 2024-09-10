package com.wora.repositories;

import com.wora.config.JdbcConnection;
import com.wora.models.dtos.CreateRegisterDto;
import com.wora.models.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserRepository implements IUserRepository {
    private final Connection connection = JdbcConnection.getInstance().getConnection();

    @Override
    public void register(CreateRegisterDto dto) {
        final String query = """
                INSERT INTO users (id, name, last_name, email, phone_number)
                VALUES (?::uuid, ?, ?, ?, ?)
                """;
        int count = 1;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setObject(count++, UUID.randomUUID());
            stmt.setString(count++, dto.name());
            stmt.setString(count++, dto.lastName());
            stmt.setString(count++, dto.email());
            stmt.setString(count++, dto.phoneNumber());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> login(String email, String name) {
        final String query = "SELECT * FROM users WHERE email = ? AND name = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, name);
            final ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapToUser(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Login failed: " + e.getMessage());
        }

        return Optional.empty();
    }


    @Override
    public List<User> search(String email, String name) throws SQLException {
        final String query = "SELECT * FROM users WHERE email = ? OR name = ?";
        List<User> users = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, name);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    User user = new User(
                            UUID.fromString(rs.getString("id")),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("last_name"),
                            rs.getString("phone_number")
                    );
                    users.add(user);
                }
            }
        }
        return users;
    }
    private User mapToUser(ResultSet rs) throws SQLException {
        return new User(
                UUID.fromString(rs.getString("id")),
                rs.getString("name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("phone_number")
        );
    }
}


