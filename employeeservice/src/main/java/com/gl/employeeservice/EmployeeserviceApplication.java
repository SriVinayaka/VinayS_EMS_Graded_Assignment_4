package com.gl.employeeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class EmployeeserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeserviceApplication.class, args);
	}

}
