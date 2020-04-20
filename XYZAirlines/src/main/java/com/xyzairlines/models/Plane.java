package com.xyzairlines.models;

import javax.persistence.*;

@Entity
@Table(name="planes")
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Airport.class)
    @JoinColumn(name="airportCode")
    private Airport airport;
    private String name;
    private int fuel = 0;

    public Plane(){}

    public Plane(Airport airport, String name, int fuel) {
        this.airport = airport;
        this.name = name;
        this.fuel = fuel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }
}

