package com.esaricoglu.flight_search_api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DtoFlight {

    private Integer id;

    private DtoAirport arrivalAirport;

    private DtoAirport departureAirport;

    private LocalDateTime returnTime;

    private Double price;

}
