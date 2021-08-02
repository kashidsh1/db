package com.sk.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @author KashidSh
 *
 */
@ComponentScan(basePackages = {"com.sk.db"})
@SpringBootApplication
public class DbstoreApplication {

	/*
	 * Main Spring Boot entry method 
	 */
	public static void main(String[] args) {
		SpringApplication.run(DbstoreApplication.class, args);
	}

}
