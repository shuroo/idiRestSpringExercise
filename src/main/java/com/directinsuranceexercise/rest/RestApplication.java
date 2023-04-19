package com.directinsuranceexercise.rest;

import com.directinsuranceexercise.rest.config.AdvertisementConfig;
import com.directinsuranceexercise.rest.model.AdManager;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.server.PWA;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * The main class of the DirectInsuranceExercise REST server application.
 * This class is responsible for starting up the server,
 * initializing the advertisements data and managing the REST API routes.
 *
 * @author shirirave
 * @since 18/04/2023
 */
@SpringBootApplication
@PWA(name = "DirectInsuranceExercise", shortName = "DirectInsuranceExercise")
@Push
public class RestApplication implements AppShellConfigurator {

    /**
     * The config class to fetch sample data from for sample data creation
     */
    @Autowired
    private AdvertisementConfig config;

    /**
     * The entry point of the DirectInsuranceExercise REST server application.
     * This method starts up the server using Spring Boot.
     * @param args The command line arguments passed to the application.
     *
     */
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

    /**
     * The method to configure a REST template bean.
     * @return A configured RestTemplate object.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }


    // Other routes and classes

    /**
     *  A method for initializing the advertisements data on server startup.
     *  It creates sample advertisements for each given type.
     */
    @PostConstruct
    public void init() {
        AdManager adManager = AdManager.getInstance();
        ConcurrentLinkedQueue allAdvertisements = adManager.getAdvertisements();
        allAdvertisements.add(config.getSampleAssetAdvertisement());
        allAdvertisements.add(config.getSampleCarAdvertisement());
        allAdvertisements.add(config.getSampleElectronicsAdvertisement());
    }
}
