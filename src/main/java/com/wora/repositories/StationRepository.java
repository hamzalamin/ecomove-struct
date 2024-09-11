package com.wora.repositories;

import com.wora.config.JdbcConnection;
import com.wora.models.dtos.CreateStationDto;
import com.wora.models.entities.Station;

import java.sql.*;
import java.util.*;

public class StationRepository implements IStationRepository {
    private final Connection connection = JdbcConnection.getInstance().getConnection();
    private final String tableName = "stations";

    @Override
    public List<Station> findAll() {
        final String query = "SELECT * FROM " + tableName;
        final List<Station> stations = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Station station = new Station(
                        UUID.fromString(rs.getString("id")),
                        rs.getString("station_name"),
                        rs.getString("city")
                );
                stations.add(station);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stations;
    }

    @Override
    public Optional<Station> findById(UUID id) throws SQLException {
        final String query = "SELECT * FROM " + tableName + " WHERE id = ?::uuid";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Station station = new Station(
                        UUID.fromString(rs.getString("id")),
                        rs.getString("station_name"),
                        rs.getString("city")
                );
                return Optional.of(station);
            }
        }
        return Optional.empty();
    }

    @Override
    public Station create(Station station) throws SQLException {
        final String query = "INSERT INTO " + tableName + " (station_name, city, id) " +
                "VALUES (?, ?, ?::uuid)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            int count = 1;
            stmt.setString(count++, station.getStationName());
            stmt.setString(count++, station.getCity());
            stmt.setObject(count++, station.getId());
            stmt.executeUpdate();
        }
        return findById(station.getId()).orElseThrow(
                () -> new RuntimeException("Station with id " + station.getId() + " could not be found")
        );
    }

    @Override
    public Station update(Station station, UUID id) throws SQLException {
        final String query = "UPDATE " + tableName + " SET " +
                "station_name = ?, " +
                "city = ? " +
                "WHERE id = ?::uuid";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            int count = 1;
            stmt.setString(count++, station.getStationName());
            stmt.setString(count++, station.getCity());
            stmt.setObject(count, station.getId());
            stmt.executeUpdate();
        }
        return findById(station.getId()).orElseThrow(
                () -> new RuntimeException("station with id " + station.getId() + " could not be found")
        );
    }

    @Override
    public void delete(UUID id) {
        final String query = "DELETE FROM " + tableName + " WHERE id = ?::uuid";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
