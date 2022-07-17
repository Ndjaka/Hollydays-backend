package com.ozone.hollidays;

import com.ozone.hollidays.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableAsync
@EnableSwagger2
public class HollidaysApplication implements CommandLineRunner {

	@Autowired
	RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(HollidaysApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		roleRepository.save(new Role(null,"USER"));
//		roleRepository.save(new Role(null,"ADMIN"));
	}
}
