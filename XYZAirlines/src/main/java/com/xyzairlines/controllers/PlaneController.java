package com.xyzairlines.controllers;

import com.xyzairlines.models.Airport;
import com.xyzairlines.models.Plane;
import com.xyzairlines.repositories.PlaneRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/planes")
public class PlaneController {

    //Inititalising plane repository and controller
    private PlaneRepository planeRepository;

    public PlaneController(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

    //Returning all planes
    @RequestMapping(method= RequestMethod.GET)
    public List<Plane> getAllPlanes() {
        return this.planeRepository.findAll();
    }

    //Returning specific plane
    @RequestMapping(path="/plane/{id}", method= RequestMethod.GET)
    public Plane getPlaneById(@PathVariable long id) {
        return this.planeRepository.findById(id).orElse(null);
    }

    //Refuelling specific plane
    @GetMapping(path="/plane/{id}/refuel")
    public String refuel(@PathVariable long id) {
        Optional<Plane> plane = this.planeRepository.findById(id);
        int currentFuel = plane.get().getFuel();
        if(currentFuel == 5) {
            return plane.get().getName() + " is already fully fuelled.";
        }
        plane.get().setFuel(5);
        this.planeRepository.save(plane.get());
        return plane.get().getName() + " has been refuelled and now has " + plane.get().getFuel() + " tons of fuel.";
    }

    //plane flies to new location- needs 2 tons of fuel
    @PostMapping(path="/plane/{id}/fly")
    public String fly(@RequestBody Plane plane) {
        int currentFuel = plane.getFuel();
        if(currentFuel < 2) {
            return plane.getName() + " only has " + plane.getFuel() + " tons of fuel and cannot fly.";
        }
        Airport currentAirport = plane.getAirport();
        plane.setAirport(plane.getAirport());
        plane.setFuel(currentFuel-2);
        this.planeRepository.save(plane);
        return plane.getName() + " has now landed in London.";
    }

    //add plane to database
    @PostMapping("/save")
    public String savePlane(@RequestBody Plane plane) {
        this.planeRepository.save(plane);
        return plane.getName() + " has been added to the database.";
    }

    //delete plane from database
    @DeleteMapping("/plane/{id}")
    public void deletePlane(@PathVariable long id) {
        planeRepository.deleteById(id);
    }
}


