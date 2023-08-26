package test.simplejwtapp.javadev;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import test.simplejwtapp.javadev.user.dto.RegisterRequest;
import test.simplejwtapp.javadev.user.service.AuthenticationService;

@SpringBootApplication
public class JavadevApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavadevApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.username("Admin")
					.password("password")
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

		};
	}

}
