package com.esaricoglu.flight_search_api.controller.impl;

import com.esaricoglu.flight_search_api.controller.IAirportController;
import com.esaricoglu.flight_search_api.dto.DtoAirport;
import com.esaricoglu.flight_search_api.dto.DtoAirportIU;
import com.esaricoglu.flight_search_api.model.Airport;
import com.esaricoglu.flight_search_api.service.IAirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/airports")
public class AirportControllerImpl implements IAirportController {

    @Autowired
    private IAirportService airportService;

    @Override
    @GetMapping("/list")
    public List<DtoAirport> findAll() {
        return airportService.findAll();
    }

    @Override
    @GetMapping("/list/{id}")
    public DtoAirport findById(@PathVariable int id) {
        return airportService.findById(id);
    }

    @Override
    @PostMapping("/save")
    public DtoAirport save(@RequestBody DtoAirportIU dtoAirportIU) {
        return airportService.save(dtoAirportIU);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable int id) {
        airportService.deleteById(id);
    }

    @Override
    @PutMapping("/update/{id}")
    public DtoAirport update(@PathVariable int id,@RequestBody DtoAirportIU dtoAirportIU) {
        return airportService.update(id, dtoAirportIU);
    }
}
