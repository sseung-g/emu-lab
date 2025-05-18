package com.practice.emulab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EmulabApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmulabApplication.class, args);
	}

}
