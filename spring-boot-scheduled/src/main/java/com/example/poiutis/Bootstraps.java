package com.example.poiutis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@ComponentScan({"com.example.poiutis"})
@SpringBootApplication
public class Bootstraps {

	public static void main(String[] args) {
		SpringApplication.run(Bootstraps.class, args);
	}

}
