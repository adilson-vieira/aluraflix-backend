package com.aluraflix;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableCaching
@SpringBootApplication
public class AluraflixApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AluraflixApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	/*	BCryptPasswordEncoder encripta = new BCryptPasswordEncoder();
		System.out.println(encripta.encode("1234"));
		// $2a$10$Z8OdeTS6NgbFpk/S5xcOS.iJwmW/T9nT0ihNT5eUCS7i.xD.NEh12
		System.out.println(encripta.encode("senha1234"));	
		// $2a$10$l9JhgfT2K6B/Qd51KTyS5.CD/HL3xyHDE23eykFtThtjuQ4.f7At6		  
	*/
	}	
}
