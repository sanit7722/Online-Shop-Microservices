package com.onelineshop.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class InventorySeviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventorySeviceApplication.class, args);
	}

}
