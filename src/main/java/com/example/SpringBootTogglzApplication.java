package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableScheduling
public class SpringBootTogglzApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTogglzApplication.class, args);

	}

}
