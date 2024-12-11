package com.esaricoglu.flight_search_api.repository;

import com.esaricoglu.flight_search_api.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
}
