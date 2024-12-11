package com.esaricoglu.flight_search_api.service.impl;

import com.esaricoglu.flight_search_api.dto.DtoAirport;
import com.esaricoglu.flight_search_api.dto.DtoFlight;
import com.esaricoglu.flight_search_api.dto.DtoFlightIU;
import com.esaricoglu.flight_search_api.model.Airport;
import com.esaricoglu.flight_search_api.model.Flight;
import com.esaricoglu.flight_search_api.repository.AirportRepository;
import com.esaricoglu.flight_search_api.repository.FlightRepository;
import com.esaricoglu.flight_search_api.service.IFlightService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements IFlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportRepository airportRepository;


    // Dto'lara dönüşüm yaparken çok fazla tekrar eden işlem yazıyorsun onlar için methodlar yaz onları çağır!!!


    @Override
    public List<DtoFlight> findAll() {
        List<Flight> flights = flightRepository.findAll();
        List<DtoFlight> dtoFlights = new ArrayList<>();

        for (Flight flight : flights) {
            DtoFlight dtoFlight = new DtoFlight();
            BeanUtils.copyProperties(flight, dtoFlight);

            DtoAirport dtoArrival = new DtoAirport();
            DtoAirport dtoDeparture = new DtoAirport();
            Airport airport = flight.getArrivalAirport();
            BeanUtils.copyProperties(airport, dtoArrival);
            dtoFlight.setArrivalAirport(dtoArrival);

            airport = flight.getDepartureAirport();
            BeanUtils.copyProperties(airport, dtoDeparture);
            dtoFlight.setDepartureAirport(dtoDeparture);

            dtoFlights.add(dtoFlight);
        }
        return dtoFlights;
    }

    @Override
    public DtoFlight findById(int id) {
        Optional<Flight> optional = flightRepository.findById(id);
        if (optional.isPresent()) {
            Flight dbFlight = optional.get();
            DtoFlight dtoFlight = new DtoFlight();
            BeanUtils.copyProperties(dbFlight, dtoFlight);

            DtoAirport dtoArrival = new DtoAirport();
            DtoAirport dtoDeparture = new DtoAirport();

            Airport airport = dbFlight.getArrivalAirport();
            BeanUtils.copyProperties(airport, dtoArrival);
            dtoFlight.setArrivalAirport(dtoArrival);

            airport = dbFlight.getDepartureAirport();
            BeanUtils.copyProperties(airport, dtoDeparture);
            dtoFlight.setDepartureAirport(dtoDeparture);

            return dtoFlight;
        }
        return null;
    }

    @Override
    public DtoFlight save(DtoFlightIU dtoFlightIU) {
        Flight flight = new Flight();
        BeanUtils.copyProperties(dtoFlightIU, flight);

        DtoFlight dtoFlight = new DtoFlight();
        DtoAirport dtoArrival = new DtoAirport();
        DtoAirport dtoDeparture = new DtoAirport();
        BeanUtils.copyProperties(flight, dtoFlight);

        Airport airport = airportRepository.findById(dtoFlightIU.getArrivalAirport().getId()).orElseThrow();
        flight.setArrivalAirport(airport);
        BeanUtils.copyProperties(airport, dtoArrival);
        dtoFlight.setArrivalAirport(dtoArrival);

        airport = airportRepository.findById(dtoFlightIU.getDepartureAirport().getId()).orElseThrow();
        flight.setDepartureAirport(airport);
        BeanUtils.copyProperties(airport, dtoDeparture);
        dtoFlight.setDepartureAirport(dtoDeparture);

        flightRepository.save(flight);

        return dtoFlight;
    }

    @Override
    public void deleteById(int id) {
        flightRepository.deleteById(id);
    }

    @Override
    public DtoFlight update(int id, DtoFlightIU dtoFlightIU) {
        Optional<Flight> optional = flightRepository.findById(id);
        if (optional.isPresent()) {
            Flight dbFlight = optional.get();
            DtoFlight dtoFlight = new DtoFlight();
            BeanUtils.copyProperties(dtoFlightIU, dbFlight);
            dbFlight.setId(id);
            BeanUtils.copyProperties(dbFlight, dtoFlight);

            DtoAirport dtoArrival = new DtoAirport();
            DtoAirport dtoDeparture = new DtoAirport();

            Airport airport = airportRepository.findById(dtoFlightIU.getArrivalAirport().getId()).orElseThrow();
            dbFlight.setArrivalAirport(airport);
            BeanUtils.copyProperties(airport, dtoArrival);
            dtoFlight.setArrivalAirport(dtoArrival);

            airport = airportRepository.findById(dtoFlightIU.getDepartureAirport().getId()).orElseThrow();
            dbFlight.setDepartureAirport(airport);
            BeanUtils.copyProperties(airport, dtoDeparture);
            dtoFlight.setDepartureAirport(dtoDeparture);

            flightRepository.save(dbFlight);

            return dtoFlight;
        }
        return null;
    }
}
