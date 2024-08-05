package com.brianeno.sample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import lombok.extern.slf4j.Slf4j;

@EnableCaching
@SpringBootApplication
@Slf4j
public class DemoApplication implements CommandLineRunner {

	@Value(value = "${url}")
	private String applicationId;

	@Value(value = "${user}")
	private String applicationTitle;

	public static void main(String[] args) {
		try {
			SpringApplication.run(DemoApplication.class, args);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public void run(String... args) throws Exception {

		log.info("user {}", applicationTitle);
		log.info("url: {}", applicationId);


	}
}
