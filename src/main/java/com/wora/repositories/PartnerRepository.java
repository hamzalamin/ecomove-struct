package com.wora.repositories;

import com.wora.config.JdbcConnection;
import com.wora.models.dtos.CreatePartnerDto;
import com.wora.models.entities.Partner;
import com.wora.models.enums.PartnerStatus;
import com.wora.models.enums.TransportType;

import java.sql.*;
import java.util.*;
import java.sql.Date;

public class PartnerRepository implements IPartnerRepository {
    private final Connection connection = JdbcConnection.getInstance().getConnection();
    private final String tableName = "partners";

    @Override
    public List<Partner> findAll() {
        final String query = "SELECT * FROM partners";
        final List<Partner> partners = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            final ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                partners.add(mapToPartner(rs));
                System.out.println("the partner found "+mapToPartner(rs).getCompanyName());
            }
            return partners;
        } catch (SQLException e) {
            throw new RuntimeException("findAll partners failed" + e.getMessage());
        }
    }

    @Override
    public Optional<Partner> findById(UUID id) {
        final String query = "SELECT * FROM partners WHERE id = ?::uuid";
        Optional<Partner> partner = Optional.empty();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setObject(1, id);
            final ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                partner = Optional.of(mapToPartner(rs));
            }
            return partner;
        } catch (SQLException e) {
            throw new RuntimeException("failed to execute query to find partner by Id" + e.getMessage());
        }

    }

    @Override
    public void create(CreatePartnerDto dto) {
        final String query = """
                INSERT INTO partners
                (company_name, contact, geographical_zone, special_condition, partner_status, transport_type, creation_date,id)
                VALUES (?, ?, ?, ?, ?::partner_status, ?::transport_types ,? , ?::uuid)
                """;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            mapToResultSet(dto, stmt, UUID.randomUUID());
            executeUpdate(stmt);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(UUID id, CreatePartnerDto dto) {
        final String query = """
                UPDATE partners
                SET company_name = ?,
                contact = ?,
                geographical_zone = ?,
                special_condition = ?,
                partner_status = ?::partner_status,
                transport_type = ?::transport_types,
                creation_date = ?
                WHERE id = ?::uuid
                """;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            mapToResultSet(dto, stmt, id);
            executeUpdate(stmt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(UUID id) {
        final String query = "DELETE FROM " + tableName + " WHERE id = ?::uuid";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setObject(1, id);
            executeUpdate(stmt);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void mapToResultSet(CreatePartnerDto dto, PreparedStatement stmt, UUID id) throws SQLException {
        int count = 1;
        stmt.setString(count++, dto.name());
        stmt.setString(count++, dto.contact());
        stmt.setString(count++, dto.geographicalZone());
        stmt.setString(count++, dto.specialCondition());
        stmt.setString(count++, dto.partnerStatus().toString());
        stmt.setString(count++, dto.transportType().toString());
        stmt.setTimestamp(count++,  new Timestamp(dto.creationDate().getTime()));
        stmt.setObject(count++, id);
    }

    private Partner mapToPartner(ResultSet rs) throws SQLException {
        return new Partner(
                UUID.fromString(rs.getString("id")),
                rs.getString("company_name"),
                rs.getString("contact"),
                rs.getString("special_condition"),
                rs.getString("geographical_zone"),
                PartnerStatus.valueOf(rs.getString("partner_status")),
                TransportType.valueOf(rs.getString("transport_type")),
                new Date(rs.getTimestamp("creation_date").getTime()),
                List.of()
        );
    }

    private void executeUpdate(PreparedStatement stmt) throws SQLException {
        int affectedRows = stmt.executeUpdate();
        if (affectedRows == 0) {
            throw new RuntimeException("no rows affected ");
        }
    }
}
