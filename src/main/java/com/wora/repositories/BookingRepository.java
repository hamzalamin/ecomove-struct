package com.wora.repositories;

import com.wora.config.JdbcConnection;
import com.wora.models.dtos.CreateBookingDto;
import com.wora.models.entities.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BookingRepository implements IBookingRepository {
    private final Connection connection = JdbcConnection.getInstance().getConnection();
    private final String tableName = "booking";


    @Override
    public List<Booking> findAll() {
        return List.of();
    }

    @Override
    public Optional<Booking> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public void create(CreateBookingDto dto) {
        final String query = "INSERT INTO " + tableName + " (id , user_id) VALUES (?::uuid, ?::uuid)";
        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setObject(1, UUID.randomUUID());
            stmt.setObject(2, dto.user());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(UUID id, CreateBookingDto dto ) {
        final  String query = """
                UPDATE bookings SET 
                user_id = ?::uuid 
                WHERE id = ?::uuid
                """;
        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setObject(1, dto.user());
            stmt.setObject(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(UUID id) {
        final String query = "DELETE FROM " + tableName + " WHERE id = ?::uuid";
        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
