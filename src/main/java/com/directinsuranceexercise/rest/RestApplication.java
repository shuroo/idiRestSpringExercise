package com.directinsuranceexercise.rest;

import com.directinsuranceexercise.rest.config.AdvertisementConfig;
import com.directinsuranceexercise.rest.model.AdManager;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ConcurrentLinkedQueue;

@SpringBootApplication
public class RestApplication {
	@Autowired
	private AdvertisementConfig config;
	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplateBuilder().build();
	}

	@PostConstruct
	//@Bean(initMethod = "init")
	public void init() {
		AdManager adManager = AdManager.getInstance();
		ConcurrentLinkedQueue allAdvertisements = adManager.getAdvertisementsList();
		allAdvertisements.add(config.getSampleAssetAdvertisement());
		allAdvertisements.add(config.getSampleCarAdvertisement());
		allAdvertisements.add(config.getSampleElectronicsAdvertisement());
	}
}
