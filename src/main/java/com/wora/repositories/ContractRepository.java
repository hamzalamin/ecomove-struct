package com.wora.repositories;

import com.wora.config.JdbcConnection;
import com.wora.models.dtos.CreateContractDto;
import com.wora.models.entities.Contract;
import com.wora.models.entities.Partner;
import com.wora.models.enums.ContractStatus;
import com.wora.models.enums.PartnerStatus;
import com.wora.models.enums.TransportType;

import java.sql.*;
import java.util.*;
import java.util.Date;


public class ContractRepository implements IContractRepository {
    private final Connection connection = JdbcConnection.getInstance().getConnection();
    private final String tableName = "contracts";

    @Override
    public List<Contract> findAll() {
        final String query = "SELECT c.*, p.* FROM contracts c INNER JOIN partners p ON c.partner_id = p.id";

        final List<Contract> contracts = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            final ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                contracts.add(mapToResulrSet(rs));
            }
            return contracts;
        } catch (SQLException e) {
            throw new RuntimeException("findAll Contracts is field" + e.getMessage());
        }
    }

    @Override
    public Optional<Contract> findById(UUID id) {
        final String query = "SELECT p.*, c.* FROM  contracts p INNER JOIN contracts c ON p.id = ?::uuid ";
        Optional<Contract> contract = Optional.empty();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setObject(1, id);
            final ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                contract = Optional.of(mapToResulrSet(rs));
            }
            return contract;
        } catch (SQLException e) {
            throw new RuntimeException("failed to execute query to find contract by Id" + e.getMessage());
        }

    }
    @Override
    public void create(CreateContractDto dto) {
        final String query = "INSERT INTO " + tableName + " (start_date, end_date, special_conditions," +
                "renewable, contract_status," +
                " partner_id, id)" +
                " VALUES (?, ?, ?, ?, ?::contract_status, ?::uuid, ?::uuid)";
        try (final PreparedStatement stmt = connection.prepareStatement(query)) {
                mapToResultSet(dto, stmt, UUID.randomUUID());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Contract inserted successfully.");
            } else {
                System.out.println("Failed to insert.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void update(CreateContractDto dto, UUID id) {
        final String query = """
                UPDATE contracts
                SET start_date = ?,
                end_date = ?,
                special_conditions = ?,
                renewable = ?,
                contract_status = ?::contract_status,
                partner_id = ?::uuid
                WHERE id = ?::uuid
                """;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            mapToResultSet(dto, stmt, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Contract inserted successfully.");
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
                System.out.println("Contract inserted successfully.");
            } else {
                System.out.println("Failed to insert.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    private Contract mapToResulrSet(ResultSet rs) throws SQLException {
        return new Contract(
                UUID.fromString(rs.getString("id")),
                new Date(rs.getTimestamp("start_date").getTime()),
                new Date(rs.getTimestamp("end_date").getTime()),
                rs.getDouble("special_conditions"),
                rs.getBoolean("renewable"),
                ContractStatus.valueOf(rs.getString("contract_status").toUpperCase()),
                new Partner(
                        UUID.fromString(rs.getString("partner_id")),
                        rs.getString("company_name"),
                        rs.getString("contact"),
                        rs.getString("special_conditions"),
                        rs.getString("geographical_zone"),
                        PartnerStatus.valueOf(rs.getString("partner_status").toUpperCase()),
                        TransportType.valueOf(rs.getString("transport_type").toUpperCase()),
                        new Date(rs.getTimestamp("creation_date").getTime()),
                        List.of()
                )
        );
    }

    private void mapToResultSet(CreateContractDto dto, PreparedStatement stmt, UUID id) throws SQLException {
        int count = 1;
        stmt.setTimestamp(count++, new Timestamp(dto.startDate().getTime()));
        stmt.setTimestamp(count++, new Timestamp(dto.endDate().getTime()));
        stmt.setDouble(count++, dto.specialCondition());
        stmt.setBoolean(count++, dto.renewable());
        stmt.setString(count++, dto.contractStatus().toString());
        stmt.setObject(count++, dto.partner().getId());
        stmt.setObject(count++, UUID.randomUUID());

    }
}
