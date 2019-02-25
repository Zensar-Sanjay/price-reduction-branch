package com.jl.product.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan("com.jl")
@EnableSwagger2
public class PriceReductionRestServiceApp {

	public static void main(String[] args) {
		SpringApplication.run(PriceReductionRestServiceApp.class, args);
	}
}
