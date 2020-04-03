package com.frknpg.hoaxifybend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class HoaxifybendApplication {

	public static void main(String[] args) {
		SpringApplication.run(HoaxifybendApplication.class, args);
	}

}
