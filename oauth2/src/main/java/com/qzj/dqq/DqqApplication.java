package com.qzj.dqq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@EnableDiscoveryClient
@SpringBootApplication
@RestController

public class DqqApplication {

	@RequestMapping("/user")
	public Principal user(Principal principal) {

		return principal;
	}

	public static void main(String[] args) {
		SpringApplication.run(DqqApplication.class, args);
	}
}
