package com.esaricoglu.flight_search_api.service.impl;

import com.esaricoglu.flight_search_api.dto.DtoAirport;
import com.esaricoglu.flight_search_api.dto.DtoAirportIU;
import com.esaricoglu.flight_search_api.dto.DtoFlight;
import com.esaricoglu.flight_search_api.model.Airport;
import com.esaricoglu.flight_search_api.model.Flight;
import com.esaricoglu.flight_search_api.repository.AirportRepository;
import com.esaricoglu.flight_search_api.repository.FlightRepository;
import com.esaricoglu.flight_search_api.service.IAirportService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AirportServiceImpl implements IAirportService {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private FlightRepository flightRepository;


    @Override
    public List<DtoAirport> findAll() {
        List<Airport> airports = airportRepository.findAll();
        List<DtoAirport> dtoAirports = new ArrayList<>();
        for (Airport airport : airports) {
            DtoAirport dtoAirport = new DtoAirport();
            List<Flight> flights = airport.getFlights();
            for (Flight flight : flights) {
                DtoFlight dtoFlight = new DtoFlight();
                BeanUtils.copyProperties(flight, dtoFlight);
                dtoAirport.getFlights().add(dtoFlight);
            }
            BeanUtils.copyProperties(airport, dtoAirport);
            dtoAirports.add(dtoAirport);
        }
        return dtoAirports;
    }

    @Override
    public DtoAirport findById(int id) {
        Optional<Airport> optional = airportRepository.findById(id);
        if (optional.isPresent()) {
            DtoAirport dtoAirport = new DtoAirport();
            BeanUtils.copyProperties(optional.get(), dtoAirport);

            List<Flight> flights = optional.get().getFlights();
            for (Flight flight : flights) {
                DtoFlight dtoFlight = new DtoFlight();
                BeanUtils.copyProperties(flight, dtoFlight);
                dtoAirport.getFlights().add(dtoFlight);
            }
            return dtoAirport;
        }
        return null;
    }

    @Override
    public DtoAirport save(DtoAirportIU dtoAirportIU) {
        Airport airport = new Airport();
        BeanUtils.copyProperties(dtoAirportIU, airport);
        if (dtoAirportIU.getFlights() != null) {
            List<DtoFlight> dtoFlights = dtoAirportIU.getFlights();
            for (DtoFlight dtoFlight : dtoFlights) {
                Flight flight = new Flight();
                BeanUtils.copyProperties(dtoFlight, flight);
                airport.getFlights().add(flight);
            }
        }

        airportRepository.save(airport);

        DtoAirport dtoAirport = new DtoAirport();
        BeanUtils.copyProperties(airport, dtoAirport);
        dtoAirport.setFlights(dtoAirportIU.getFlights());

        return dtoAirport;
    }

    @Override
    public void deleteById(int id) {
        airportRepository.deleteById(id);
    }

    @Override
    public DtoAirport update(int id, DtoAirportIU dtoAirportIU) {
        Optional<Airport> optional = airportRepository.findById(id);
        if (optional.isPresent()) {
            Airport dbAirport = optional.get();
            BeanUtils.copyProperties(dtoAirportIU, dbAirport);
            dbAirport.setId(id);

            if (dtoAirportIU.getFlights() != null) {
                List<DtoFlight> dtoFlights = dtoAirportIU.getFlights();
                for (DtoFlight dtoFlight : dtoFlights) {
                    Flight flight = new Flight();
                    BeanUtils.copyProperties(dtoFlight, flight);
                    dbAirport.getFlights().add(flight);
                }
            }

            airportRepository.save(dbAirport);

            DtoAirport dtoAirport = new DtoAirport();
            BeanUtils.copyProperties(dbAirport, dtoAirport);
            dtoAirport.setFlights(dtoAirportIU.getFlights());
            return dtoAirport;
        }
        return null;
    }

}
