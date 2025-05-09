package com.celiahelp;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CeliahelpApplication {

	public static void main(String[] args) {
		SpringApplication.run(CeliahelpApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("CeliaHelp API")
						.version("v0.0.1")
						.description("API de gestión de incidencias de CeliaHelp"));
	}

}
