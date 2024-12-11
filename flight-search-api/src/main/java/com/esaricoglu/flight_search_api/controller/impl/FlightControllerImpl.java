package com.esaricoglu.flight_search_api.controller.impl;

import com.esaricoglu.flight_search_api.controller.IFlightController;
import com.esaricoglu.flight_search_api.dto.DtoFlight;
import com.esaricoglu.flight_search_api.dto.DtoFlightIU;
import com.esaricoglu.flight_search_api.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/flights")
public class FlightControllerImpl implements IFlightController {

    @Autowired
    private IFlightService flightService;

    @Override
    @GetMapping("/list")
    public List<DtoFlight> findAll() {
        return flightService.findAll();
    }

    @Override
    @GetMapping("/list/{id}")
    public DtoFlight findById(@PathVariable int id) {
        return flightService.findById(id);
    }

    @Override
    @PostMapping("/save")
    public DtoFlight save(@RequestBody DtoFlightIU dtoFlightIU) {
        return flightService.save(dtoFlightIU);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable int id) {
        flightService.deleteById(id);
    }

    @Override
    @PutMapping("/update/{id}")
    public DtoFlight update(@PathVariable int id,@RequestBody DtoFlightIU dtoFlightIU) {
        return flightService.update(id, dtoFlightIU);
    }
}
