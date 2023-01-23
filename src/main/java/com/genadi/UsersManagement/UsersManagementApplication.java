package com.genadi.UsersManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.genadi.UsersManagement")
public class UsersManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersManagementApplication.class, args);
	}

}
