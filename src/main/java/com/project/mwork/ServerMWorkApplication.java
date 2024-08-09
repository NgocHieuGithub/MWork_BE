package com.project.mwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServerMWorkApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerMWorkApplication.class, args);
		System.out.println("Application start ......");
	}

}
