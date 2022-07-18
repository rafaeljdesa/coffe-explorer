package br.com.coffe.explorer.coffe.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CoffeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffeServiceApplication.class, args);
	}

}
