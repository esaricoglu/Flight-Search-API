package com.esaricoglu.flight_search_api.service;

import com.esaricoglu.flight_search_api.dto.DtoAirport;
import com.esaricoglu.flight_search_api.dto.DtoAirportIU;
import com.esaricoglu.flight_search_api.model.Airport;

import java.util.List;

public interface IAirportService {

    List<DtoAirport> findAll();

    DtoAirport findById(int id);

    DtoAirport save(DtoAirportIU dtoAirportIU);

    void deleteById(int id);

    DtoAirport update(int id, DtoAirportIU dtoAirportIU);
}
