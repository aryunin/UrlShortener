package ru.aryunin.UrlShortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class UrlShortenerApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(UrlShortenerApplication.class);
		if(Arrays.asList(args).contains("-d")) springApplication.setAdditionalProfiles("inmemory");
		else springApplication.setAdditionalProfiles("postgres");
		springApplication.run(args);
	}

}
