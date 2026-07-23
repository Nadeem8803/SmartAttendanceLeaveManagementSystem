package com.manager.attendenceleavemanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AttendenceLeaveManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendenceLeaveManagementSystemApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("Rehan1234"));
	}

}
