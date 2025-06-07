package com.drugstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
@SpringBootApplication
public class DrugstoreApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(DrugstoreApplication.class, args);
	}

}
