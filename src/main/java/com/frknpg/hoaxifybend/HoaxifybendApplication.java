package com.frknpg.hoaxifybend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class HoaxifybendApplication {

	public static void main(String[] args) {
		SpringApplication.run(HoaxifybendApplication.class, args);
	}


	// Uygulama basladiignda bir user olusturuyor
//	@Bean
//	CommandLineRunner createInitialUser(IUserService userService) {
//		return args -> {
//			User user = new User();
//			user.setUsername("user1");
//			user.setDisplayName("user1");
//			user.setPassword("P4ssword");
//			userService.save(user);
//		};
//	}

}
