package com.wora.repositories;

import com.wora.config.JdbcConnection;
import com.wora.models.entities.Route;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RouteRepository implements IRouteRepository{
    private final Connection connection = JdbcConnection.getInstance().getConnection();
    private final String tableName = "routes";

    @Override
    public List<Route> findAll() {
        final String query = "SELECT * FROM " + tableName;
        final List<Route> routes = new ArrayList<>();
        try (Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Route route = new Route(
                    UUID.fromString(rs.getString("id")),
                    UUID.fromString(rs.getString("departed_id")),
                    UUID.fromString(rs.getString("destination_id")),
                    rs.getDouble("distance")
                );
            routes.add(route);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return routes;
    }

    @Override
    public Optional<Route> findById(UUID id) throws SQLException {
        final String query = "SELECT * FROM " + tableName + " WHERE id = ?::uuid";
        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                Route route = new Route(
                        UUID.fromString(rs.getString("id")),
                        UUID.fromString(rs.getString("departed_id")),
                        UUID.fromString(rs.getString("destination_id")),
                        rs.getDouble("distance")
                );
                return Optional.of(route);
            }
        }
        return Optional.empty();
    }

    @Override
    public Route create(Route route) throws SQLException {
        final String query = "INSERT INTO " +tableName+ " (id, departed_id, destination_id, distance) " +
                "VALUES (?::uuid, ?::uuid, ?::uuid, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)){
            int count = 1;
            stmt.setObject(count++, route.getId());
            stmt.setObject(count++, route.getDepartedId());
            stmt.setObject(count++, route.getDestinationId());
            stmt.setDouble(count++, route.getDistance());
            stmt.executeUpdate();
        }
        return findById(route.getId()).orElseThrow(
                () -> new RuntimeException("Route with id " + route.getId() + " could not be found")
        );
    }

    @Override
    public Route update(Route route, UUID id) throws SQLException {
        final String query = """
                UPDATE routes SET 
                departed_id = ?::uuid,
                destination_id = ?::uuid,
                distance = ?
                WHERE id = ?::uuid 
                """;
        try (PreparedStatement stmt = connection.prepareStatement(query)){
            int count = 1;
            stmt.setObject(count++, route.getDepartedId());
            stmt.setObject(count++, route.getDestinationId());
            stmt.setDouble(count++, route.getDistance());
            stmt.setObject(count++, route.getId());
            stmt.executeUpdate();
        }
        return findById(route.getId()).orElseThrow(
                () -> new RuntimeException("Route with id " + route.getId() + " could not be found")
        );
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
