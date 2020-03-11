package com.example.demo;

import org.springframework.boot.SpringApplication;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//@EnableScheduling
public class Servicespring1Application extends SpringBootServletInitializer
{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder app)
	{
		return app.sources(Servicespring1Application.class);
	}

	public static void main(String[] args)
	{
		SpringApplication.run(Servicespring1Application.class, args);
	}

}
