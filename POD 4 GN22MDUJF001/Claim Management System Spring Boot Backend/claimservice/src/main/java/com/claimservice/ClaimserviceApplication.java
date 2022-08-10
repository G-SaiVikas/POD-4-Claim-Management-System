package com.claimservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ClaimserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClaimserviceApplication.class, args);
	}

}
