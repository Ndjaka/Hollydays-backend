package com.ozone.hollidays;

import com.ozone.hollidays.repositories.RoleRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
@OpenAPIDefinition(info = @Info(title = "Holiday API", version = "1.0", description = "Holiday Information"))
@SecurityScheme(name = "Authorization", scheme = "Bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class HollidaysApplication implements CommandLineRunner {

	final
	RoleRepository roleRepository;

	public HollidaysApplication(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(HollidaysApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
 //		roleRepository.save(new Role(null,"USER"));
// 		roleRepository.save(new Role(null,"ADMIN"));
	}
}
