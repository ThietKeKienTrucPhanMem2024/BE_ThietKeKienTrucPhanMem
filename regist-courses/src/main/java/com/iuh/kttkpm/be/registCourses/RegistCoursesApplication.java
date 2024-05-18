package com.iuh.kttkpm.be.registCourses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class RegistCoursesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistCoursesApplication.class, args);
	}

}
