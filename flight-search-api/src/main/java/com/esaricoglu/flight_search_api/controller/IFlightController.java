package com.esaricoglu.flight_search_api.controller;

import com.esaricoglu.flight_search_api.dto.DtoFlight;
import com.esaricoglu.flight_search_api.dto.DtoFlightIU;
import com.esaricoglu.flight_search_api.model.Flight;

import java.util.List;

public interface IFlightController {

    List<DtoFlight> findAll();

    DtoFlight findById(int id);

    DtoFlight save(DtoFlightIU dtoFlightIU);

    void deleteById(int id);

    DtoFlight update(int id, DtoFlightIU dtoFlightIU);
}
