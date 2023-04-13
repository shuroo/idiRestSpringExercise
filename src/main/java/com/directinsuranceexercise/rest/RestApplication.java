package com.directinsuranceexercise.rest;

import com.directinsuranceexercise.rest.config.AdvertisementConfig;
import com.directinsuranceexercise.rest.model.AdManager;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ConcurrentLinkedQueue;

@SpringBootApplication
//@Theme(value = Lumo.class, variant = Lumo.DARK)
@PWA(name = "DirectInsuranceExercise", shortName = "DirectInsuranceExercise")
//@CssImport(value = "./styles/shared-styles.css")
@Push
public class RestApplication implements AppShellConfigurator {
	@Autowired
	private AdvertisementConfig config;

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplateBuilder().build();
	}


	// Other routes and classes

	@PostConstruct
	public void init() {
		AdManager adManager = AdManager.getInstance();
		ConcurrentLinkedQueue allAdvertisements = adManager.getAdvertisementsList();
		allAdvertisements.add(config.getSampleAssetAdvertisement());
		allAdvertisements.add(config.getSampleCarAdvertisement());
		allAdvertisements.add(config.getSampleElectronicsAdvertisement());
	}
}
