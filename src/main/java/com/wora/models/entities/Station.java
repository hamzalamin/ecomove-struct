package com.wora.models.entities;

import com.wora.presentation.StationUi;

import java.util.UUID;

public class Station {
    private UUID id;
    private String stationName;
    private String city;

    public Station(UUID id, String stationName, String city) {
        this.id = id;
        this.stationName = stationName;
        this.city = city;
    }

    public Station (String stationName, String city) {
        this(UUID.randomUUID(), stationName, city);
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
