package com.esaricoglu.flight_search_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoAirportIU {

    private Integer id;

    private String city;

    private List<DtoFlight> flights;
}
