package com.policyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PolicyserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PolicyserviceApplication.class, args);
	}

}
