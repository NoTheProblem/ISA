package ftn.isa.pharmacy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PharmacyApplication {
	public static void main(String[] args) {
		SpringApplication.run(PharmacyApplication.class, args);
	}

}
