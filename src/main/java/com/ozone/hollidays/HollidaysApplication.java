package com.ozone.hollidays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class HollidaysApplication {

	public static void main(String[] args) {
		SpringApplication.run(HollidaysApplication.class, args);
	}

}
