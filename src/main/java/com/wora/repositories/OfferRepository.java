package com.wora.repositories;

import com.wora.config.JdbcConnection;
import com.wora.models.dtos.CreateOfferDto;
import com.wora.models.entities.Contract;
import com.wora.models.entities.Offer;
import com.wora.models.enums.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OfferRepository {
    private final Connection connection = JdbcConnection.getInstance().getConnection();
    private final String tableName = "offers";

    public List<Offer> findAll() {
        final String query = "SELECT o.id AS offer_id, o.offer_name, o.description, o.start_date, o.end_date,\n" +
                "       o.discount_value, o.conditions, o.reduction_type, o.offer_status,\n" +
                "       c.id AS contract_id, c.start_date AS contract_start_date, c.end_date AS contract_end_date,\n" +
                "       c.special_conditions, c.renewable, c.contract_status\n" +
                "FROM Offers o\n" +
                "INNER JOIN Contracts c ON o.contact_id = c.id\n";
        final List<Offer> Offers = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            final ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Offers.add(mapToResultSet(rs));
            }
            return Offers;
        } catch (SQLException e) {
            throw new RuntimeException("findAll Offers is field" + e.getMessage());
        }
    }

    public Optional<Offer> findById(UUID id) {
        final String query = "SELECT *\n" +
                "FROM offers " +
                "WHERE c.id = ?::uuid;";


        Optional<Offer> offer = Optional.empty();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setObject(1, id);
            final ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                offer = Optional.of(mapToResultSet(rs));
            }
            return offer;
        } catch (SQLException e) {
            throw new RuntimeException("failed to execute query to find partner by Id" + e.getMessage());
        }

    }

    public void create(CreateOfferDto dto) {
        final String query = "INSERT INTO " + tableName + " (offer_name, description, start_date," +
                " end_date, discount_value, conditions, reduction_type, offer_status, " +
                " contact_id, id)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?::reduction_types, ?::offer_status, ?::uuid ,?::uuid)";
        try (final PreparedStatement stmt = connection.prepareStatement(query)) {
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

    public void update(CreateOfferDto dto) {
        final String query = """
                UPDATE offers
                SET offer_name = ?,
                description = ?,
                start_date = ?,
                end_date = ?,
                discount_value = ?,
                conditions = ?,
                reduction_type = ?::reduction_types,
                offer_status = ?::offer_status,
                contact_id = ?::uuid
                WHERE id = ?::uuid
                """;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
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

    public void delete(UUID id) {
        final String query = "DELETE FROM " + tableName + " WHERE id = ?::uuid";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setObject(1, id);
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


    private Offer mapToResultSet(ResultSet rs) throws SQLException {
        return new Offer(
                UUID.fromString(rs.getString("offer_id")),
                rs.getString("offer_name"),
                rs.getString("description"),
                new java.util.Date(rs.getTimestamp("start_date").getTime()),
                new java.util.Date(rs.getTimestamp("end_date").getTime()),
                rs.getDouble("discount_value"),
                rs.getString("conditions"),
                ReductionType.valueOf(rs.getString("reduction_type")),
                OfferStatus.valueOf(rs.getString("offer_status")),
                new Contract(
                        UUID.fromString(rs.getString("contract_id")),
                        new Date(rs.getTimestamp("contract_start_date").getTime()),
                        new Date(rs.getTimestamp("contract_end_date").getTime()),
                        rs.getDouble("special_conditions"),
                        rs.getBoolean("renewable"),
                        ContractStatus.valueOf(rs.getString("contract_status")),
                        null
                )
        );
    }


    private void mapToResultSet(CreateOfferDto dto, PreparedStatement stmt) throws SQLException {
        int count = 1;
        stmt.setString(count++, dto.offerName());
        stmt.setString(count++, dto.description());
        stmt.setTimestamp(count++, new Timestamp(dto.startDate().getTime()));
        stmt.setTimestamp(count++, new Timestamp(dto.endDate().getTime()));
        stmt.setDouble(count++, dto.discountValue());
        stmt.setString(count++, dto.conditions());
        stmt.setString(count++, dto.reductionType().toString());
        stmt.setString(count++, dto.offerStatus().toString());
        stmt.setObject(count++, dto.contractId());
        stmt.setObject(count++, UUID.randomUUID());

    }
}
