package com.frknpg.hoaxifybend;

import com.frknpg.hoaxifybend.hoax.IHoaxService;
import com.frknpg.hoaxifybend.hoax.vm.HoaxSubmitVM;
import com.frknpg.hoaxifybend.user.IUserService;
import com.frknpg.hoaxifybend.user.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication()
public class HoaxifybendApplication {

	public static void main(String[] args) {
		SpringApplication.run(HoaxifybendApplication.class, args);
	}


	@Bean
	@Profile("dev")
	CommandLineRunner createInitialUser(IUserService userService, IHoaxService hoaxService) {
		return args -> {
			for (int i = 1; i <= 10; i++) {
				User user = new User();
				user.setUsername("user" + i);
				user.setDisplayName("display" + i);
				user.setPassword("P4ssword");
				userService.save(user);
				for (int j = 1; j <= 15; j++) {
					HoaxSubmitVM hoax = new HoaxSubmitVM();
					hoax.setContent("Hoax - " + j + " from user " + i);
					hoaxService.saveHoax(hoax, user);
				}
			}
		};
	}

}
