package com.eqrest.EarthquakeREST;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;

@SpringBootApplication
@OpenAPIDefinition(
		info=@Info(title="Earthquakes REST API", version = "1.0.0"),
		servers = {@Server(url="http://localhost:8080")}
		)
public class EarthquakeRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(EarthquakeRestApplication.class, args);
	}

}
