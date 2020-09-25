package com.client.productview;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProductviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductviewApplication.class, args);
	}

}
