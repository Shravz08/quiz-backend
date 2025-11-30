package com.quizapp;

import org.springframework.boot.SpringApplication;
	import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

	@SpringBootApplication
	public class QuizappApplication {

		public static void main(String[] args) {
			SpringApplication.run(QuizappApplication.class, args);
			
			System.out.println("It's workinggg");
		
	        System.out.println(new BCryptPasswordEncoder().encode("12345"));

		}
}
