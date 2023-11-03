package com.brianeno.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@Slf4j
public class DemoApplication implements CommandLineRunner {

	@Value(value = "${application.id}")
	private String applicationId;

	@Value(value = "${application.title:#{null}}")
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

		log.info("Resolved message title: {}", applicationTitle);
		log.info("Resolved message id: {}", applicationId);


	}
}
