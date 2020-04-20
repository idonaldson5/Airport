package com.xyzairlines.models;

import javax.persistence.*;

//Create airport entity to limit available airports to possible destinations
@Entity
@Table(name="airports")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String airportName, airportCode;

    public Airport(){}

    public Airport(String airportName, String airportCode) {
        this.airportName = airportName;
        this.airportCode = airportCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    @Override
    public String toString() {
        return airportName;
    }
}
