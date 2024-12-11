package com.esaricoglu.flight_search_api.starter;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.esaricoglu.flight_search_api"})
@ComponentScan(basePackages = {"com.esaricoglu.flight_search_api"})
@EnableJpaRepositories(basePackages = {"com.esaricoglu.flight_search_api"})
public class FlightSearchApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightSearchApiApplication.class, args);
    }

}
