package com.directinsuranceexercise.rest.model;

import com.directinsuranceexercise.rest.config.AdvertisementConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to manage the Ads data structure (List) as a singleton
 */
public class AdManager {
    @Autowired
    protected AdvertisementConfig config;
    private static AdManager instance = null;

    // Create an in-memory list to store the GenericAdvertisement objects
    private List<GenericAdvertisement> allAdvertisements;

    private AdManager() {
        allAdvertisements = new ArrayList<>();
    }

    public static AdManager getInstance() {
        if (instance == null) {
            instance = new AdManager();
        }
        return instance;
    }

    public List<GenericAdvertisement> getAdvertisementsList() {
        return allAdvertisements;
    }

    public GenericAdvertisement findById(Long requestedId) {
        if (requestedId != null) {
            return allAdvertisements.stream()
                    .filter(ad -> ad.getId() == requestedId)
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    protected void generateAndSetId(GenericAdvertisement assetAdvertisement) {
        Long id = (long) (allAdvertisements.size() + 1);
        assetAdvertisement.setId(id);
    }


    @Bean
    public static CommonAnnotationBeanPostProcessor commonAnnotationBeanPostProcessorAsset() {

        // singleton pattern:
        return new CommonAnnotationBeanPostProcessor();
    }
    Integer counter = 0;
    @PostConstruct
    @Bean(initMethod = "init")
    public void init() {

        if(counter == 0) {
            System.out.println("=====1====");
            allAdvertisements.add(config.getSampleAssetAdvertisement());
            allAdvertisements.add(config.getSampleCarAdvertisement());
            allAdvertisements.add(config.getSampleElectronicsAdvertisement());
            counter++;
        }
    }
}
