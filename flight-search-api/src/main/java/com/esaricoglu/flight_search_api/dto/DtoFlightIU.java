package com.esaricoglu.flight_search_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoFlightIU {

    private Integer id;

    private DtoAirport arrivalAirport;

    private DtoAirport departureAirport;

    private LocalDateTime returnTime;

    private Double price;
}
