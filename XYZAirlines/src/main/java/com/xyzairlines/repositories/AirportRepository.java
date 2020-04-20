package com.xyzairlines.repositories;

import com.xyzairlines.models.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {

    Airport findByAirportCode(String airportCode);
}
