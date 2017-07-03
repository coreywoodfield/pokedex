package com.coreywoodfield.pokedex;


import com.mashape.unirest.http.Unirest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by cwoodfie on 3/17/16.
 */
@SpringBootApplication
public class Application {
	public static void main(String[] args) throws Exception {
		Unirest.setDefaultHeader("Accept", "application/json");
		Unirest.setDefaultHeader("User-Agent", "java");
		SpringApplication.run(Application.class, args);
	}
}
