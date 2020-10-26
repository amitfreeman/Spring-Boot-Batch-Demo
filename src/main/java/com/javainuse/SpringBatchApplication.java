package com.javainuse;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchApplication {

	public static void main(String[] args) {
		System.out.println("#AMIT, in SpringBatchApplication main");
		SpringApplication.run(SpringBatchApplication.class, args);
	}
}