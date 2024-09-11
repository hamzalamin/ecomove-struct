package com.wora.repositories;

import com.wora.config.JdbcConnection;
import com.wora.models.dtos.CreateTicketDto;
import com.wora.models.entities.Ticket;
import com.wora.models.enums.TicketStatus;

import java.sql.*;
import java.util.*;

public class TicketRepository implements ITicketRepository {
    private final Connection connection = JdbcConnection.getInstance().getConnection();
    private final String tableName = "tickets";

    @Override
    public List<Ticket> findAll(){
        final String query = "SELECT * FROM " + tableName;
        final List<Ticket> tickets = new ArrayList<>();
        try (Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                tickets.add(mapToResulrSet(rs));
            }
            return tickets;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Ticket> findById(UUID id) {
        final String query = "SELECT * FROM tickets WHERE id = ?::uuid";
        Optional<Ticket> ticket = Optional.empty();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setObject(1, id);
            final ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ticket = Optional.of(mapToResulrSet(rs));
            }
            return ticket;
        } catch (SQLException e) {
            throw new RuntimeException("failed to execute query to find partner by Id" + e.getMessage());
        }

    }

    @Override
    public void create(CreateTicketDto dto){
        final String query = "INSERT INTO "
                + tableName +
                " (purchase_price, sale_price, sale_date,ticket_status, id)" +
                "VALUES" +
                "(?, ?, ?,?::ticket_status, ?::uuid)";
        try (final PreparedStatement stmt = connection.prepareStatement(query)){
            mapToResultSet(dto, stmt);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Offer inserted successfully.");
            } else {
                System.out.println("Failed to insert.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(CreateTicketDto dto) {
        final String query = """
                UPDATE tickets
                SET purchase_price = ?,
                sale_price = ?,
                sale_date = ?,
                ticket_status = ?::ticket_status
                WHERE id = ?::uuid
                """;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            mapToResultSet(dto, stmt);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Ticket inserted successfully.");
            } else {
                System.out.println("Failed to insert.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(UUID id) {
        final String query = "DELETE FROM " + tableName + " WHERE id = ?::uuid";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setObject(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Ticket inserted successfully.");
            } else {
                System.out.println("Failed to insert.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private Ticket mapToResulrSet(ResultSet rs) throws SQLException {
        return new Ticket(
                (UUID) rs.getObject("id"),
                rs.getDouble("purchase_price"),
                rs.getDouble("sale_price"),
                rs.getDate("sale_date"),
                TicketStatus.valueOf(rs.getString("ticket_status"))
        );
    }

    private void mapToResultSet(CreateTicketDto dto,PreparedStatement stmt) throws SQLException {
        int count = 1;
        stmt.setDouble(count++, dto.purchasePrice());
        stmt.setDouble(count++,  dto.salePrice());
        stmt.setTimestamp(count++, new Timestamp(dto.saleDate().getTime()));
        stmt.setString(count++, dto.ticketStatus().toString());
        stmt.setObject(count++, UUID.randomUUID());
    }
}
