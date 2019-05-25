package com.java.pgs.wypozyczalnia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class WypożyczalniaSprzętuApplication{


	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WypożyczalniaSprzętuApplication.class);
	}


	public static void main(String[] args) throws Throwable  {
		SpringApplication.run(WypożyczalniaSprzętuApplication.class, args);
	}




}
