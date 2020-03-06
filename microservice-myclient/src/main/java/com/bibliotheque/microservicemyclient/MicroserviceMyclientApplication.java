package com.bibliotheque.microservicemyclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.bibliotheque.microservicemyclient")
@EnableDiscoveryClient
public class MicroserviceMyclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceMyclientApplication.class, args);
	}

}
