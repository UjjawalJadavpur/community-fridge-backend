package com.fridge.community_fridge_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CommunityFridgeBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunityFridgeBackendApplication.class, args);
	}

}
