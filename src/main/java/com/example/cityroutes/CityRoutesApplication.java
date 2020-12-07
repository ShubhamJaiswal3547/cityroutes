package com.example.cityroutes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.example")
public class CityRoutesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityRoutesApplication.class, args);
	}

}
