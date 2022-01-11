package com.spring.BackendProject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@OpenAPIDefinition
public class BackendProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendProjectApplication.class, args);
	}

}
